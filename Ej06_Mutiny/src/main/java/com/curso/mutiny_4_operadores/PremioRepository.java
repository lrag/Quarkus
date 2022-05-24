package com.curso.mutiny_4_operadores;

//@Component
public class PremioRepository {

	/*
	@Autowired
	private DataSource dataSource;
	
	public Flux<Premio> findAllByIdPelicula(Integer idPelicula){
		
		Flux<Premio> flux = Flux.generate(
				//State supplier
				() -> {
					Connection cx = dataSource.getConnection();
					PreparedStatement pst = cx.prepareStatement("select * from premio where fk_id_pelicula=?");
					pst.setInt(1, idPelicula);
					pst.setFetchSize(1);
					ResultSet rs = pst.executeQuery();
					return rs;
				},
				//Generator
				(rs, consumidores) -> {	
					try {
						if(rs.next()) {
							Premio p = new Premio(
									rs.getInt("ID"),
									rs.getString("NOMBRE"),
									rs.getString("FECHA"),
									rs.getInt("FK_ID_PELICULA")
								);
							consumidores.next(p);
							Thread.sleep(500);
						} else {
							consumidores.complete();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}					
					return rs;
				},
				//State consumer
				(rs) -> {
					System.out.println("Cerrando la conexión");
					try {
						rs.getStatement().getConnection().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}				
			);		
		return flux;
	}
	*/
}
