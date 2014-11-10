package menu;

public class MenuTouchManager {

	public static MenuTouchManager instance;
	
	private MenuTouchManager(){
		
	}
	public static MenuTouchManager getInstance(){
		if(instance == null){
			instance = new MenuTouchManager();
		}
		return instance;
	}
	
	public void onClick(int x, int y){
		boolean flag = false;
		for (int i = 0; i<MenuInterfaceManager.getInstance().b.size() && !flag ; i++){
			flag = MenuInterfaceManager.getInstance().b.get(i).isClicked(x, y);
		}
		if(flag){
			MenuInterfaceManager.getInstance().b.clear();
		}
	}
}
