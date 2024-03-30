package DTO;

public class OnlineCourseDTO extends courseDTO{
	private String url;

	public OnlineCourseDTO(int id, String tittle, int credits, int maKhoa,String url) {
		super( id,tittle,credits, maKhoa);
		this.url = url;
	}

	public OnlineCourseDTO() {
		super();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
