package com.curso.mutiny_4_operadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

import com.curso.modelo.entidad.Pelicula;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class PeliculaRepository {

	@Inject
	private DataSource dataSource;
	
	public Multi<Pelicula> findAll_Reactivo(){		
		return Multi.createFrom().emitter(
			(emitter) -> {
				try (Connection cx = dataSource.getConnection()) {
					PreparedStatement pst = cx.prepareStatement("select * from pelicula");
					ResultSet rs = pst.executeQuery();
					while(rs.next()) {
						Pelicula p = new Pelicula(
								rs.getInt("ID"),
								rs.getString("TITULO"),
								rs.getString("DIRECTOR"),
								rs.getString("GENERO"),
								rs.getInt("YEAR")
							);
						emitter.emit(p);	
						Thread.sleep(500);
					}
					pst.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					emitter.fail(e);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
				emitter.complete();
			});		
	}
	
	public Uni<Pelicula> findById(Integer idPelicula) {
		
		return Uni.createFrom().emitter(emitter -> {			
			try(Connection cx = dataSource.getConnection()) {
				PreparedStatement pst = cx.prepareStatement("select * from pelicula where id=?");
				pst.setInt(1, idPelicula);
				ResultSet rs = pst.executeQuery();
				
				if(rs.next()) {
					Pelicula p = new Pelicula(
							rs.getInt("ID"),
							rs.getString("TITULO"),
							rs.getString("DIRECTOR"),
							rs.getString("GENERO"),
							rs.getInt("YEAR")
						);						
					emitter.complete(p);	
				} else {
					Exception e = new Exception("No existe una pelicula con ese id");
					emitter.fail(e);
				}
				pst.close();
				rs.close();
			} catch (SQLException e) {				
				emitter.fail(e);
			}	
		});	
	}	
	
	public Uni<List<Pelicula>> findAllById(Integer...ids) {
		List<Uni<Pelicula>> unis = new ArrayList<>();
		for(Integer id: ids) {
			unis.add(findById(id));
		}	
		return Uni.join().all(unis).andCollectFailures();
	}	
	
}
