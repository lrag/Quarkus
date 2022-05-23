package com.curso.mutiny_2_generadores;

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

@ApplicationScoped
public class PeliculaRepositorio {

	@Inject
	private DataSource dataSource;
	
	public List<Pelicula> findAll(){	
		List<Pelicula> peliculas = new ArrayList<>();
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
				peliculas.add(p);	
				Thread.sleep(500);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		return peliculas;
	}
	
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
				} catch (SQLException e) {
					e.printStackTrace();
					emitter.fail(e);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
				emitter.complete();
			});		
	}
	
}
