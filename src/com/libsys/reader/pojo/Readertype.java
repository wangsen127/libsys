package com.libsys.reader.pojo;
/**
 * Readertype��POJO�������洢��tb_readertype�е�����
 *
 */
public class Readertype {

	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return name;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Readertype other = (Readertype) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
