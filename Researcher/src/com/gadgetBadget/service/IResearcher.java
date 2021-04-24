/**
 * 
 */
package com.gadgetBadget.service;

import java.util.ArrayList;

import com.gadgetBadget.model.Researcher;

/**
 * @author chithu
 *
 */
public interface IResearcher {
	public boolean addResearcher(Researcher researcher); 
	public boolean updateResearcher(Researcher researcher);
	public boolean deleteResearcher(int ResearcherId);
	public ArrayList<Researcher> getReacherList();
	public Researcher getResearcher(int id);

}
