package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.DaoFactory;
import model.Section;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.ScheduleOfClassesService;
import service.dto.AjaxResult;
import service.dto.ListBoxDto;

public class ScheduleOfClassAction {
	private String semester;
	private JSONArray jsonArray = new JSONArray();
	private List<Section> sections;
	private ScheduleOfClassesService scs ;
	
	public JSONArray getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	public ScheduleOfClassesService getScs() {
		return scs;
	}
	public void setScs(ScheduleOfClassesService scs) {
		this.scs = scs;
	}
	public String query(){
		System.out.println(semester);
		scs = new ScheduleOfClassesService(semester, DaoFactory.createSectioneDao());
		sections = scs.getScheduleOfClasses().getSectionsOffered();
		for(Section sec : sections){
			JSONObject jo = new JSONObject();
			jo.put("sectionNo", sec.getSectionNo());
			jo.put("courseNo", sec.getRepresentedCourse().getCourseNo());
			jo.put("courseName", sec.getRepresentedCourse().getCourseName());
			jo.put("dayOfWeek", sec.getDayOfWeek());
			jo.put("timeOfDay", sec.getTimeOfDay());
			jo.put("room", sec.getRoom());
			jo.put("seatingCapacity", sec.getSeatingCapacity());
			jo.put("professor", sec.getInstructor().getName());
			jsonArray.add(jo);			
		}
		return "JSONRESULT";

	}
}
