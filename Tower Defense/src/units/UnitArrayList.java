package units;

import java.util.ArrayList;
import java.util.Collections;
import ar.edu.itba.TowerDefense.CustomArrayList;


public class UnitArrayList extends CustomArrayList<Unit, UnitUI> {

	public UnitArrayList(){
		back  = new ArrayList<Unit>(50);
		front = new ArrayList<UnitUI>(50);
		
	}
	public void sort(){
		Collections.sort(back);
		Collections.sort(front);
	}
}
