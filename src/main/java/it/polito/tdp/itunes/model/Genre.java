package it.polito.tdp.itunes.model;

public class Genre {
	private Integer genreId;
	private String name;
	private double durataMinima;
	
	public Genre(Integer genreId, String name, Double durataMinima) {
		super();
		this.genreId = genreId;
		this.name = name;
		this.durataMinima = durataMinima;
	}
	
	

	public double getDurataMinima() {
		return durataMinima;
	}

	public void setDurataMinima(double durataMinima) {
		this.durataMinima = durataMinima;
	}

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genreId == null) ? 0 : genreId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		if (genreId == null) {
			if (other.genreId != null)
				return false;
		} else if (!genreId.equals(other.genreId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	
	
	
	
}