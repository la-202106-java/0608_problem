package la.bean;

public class MaterialCategory {
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

	public MaterialCategory(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
