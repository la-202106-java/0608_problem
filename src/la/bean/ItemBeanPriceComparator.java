package la.bean;

import java.util.Comparator;

public class ItemBeanPriceComparator implements Comparator<ItemBean> {

	@Override
	public int compare(ItemBean o1, ItemBean o2) {

		return Integer.valueOf(o1.getPrice()).compareTo(o2.getPrice());
	}

}
