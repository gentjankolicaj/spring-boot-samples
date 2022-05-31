package springboot.samples.jwt01.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="file_type")
public class FileType implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="extension")
	private String extension;
	
	public FileType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileType(Integer id, String type, String extension) {
		super();
		this.id = id;
		this.type = type;
		this.extension = extension;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	

}
