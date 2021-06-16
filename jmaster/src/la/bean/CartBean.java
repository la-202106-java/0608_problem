package la.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CartBean {
	private Map<Integer, ItemBean2> items = new HashMap<Integer, ItemBean2>();
	private int total;

	public CartBean() {
	}

	public Map<Integer, ItemBean2> getItems() {
		return items;
	}

	public void addCart(ItemBean2 bean, int nums) {
		ItemBean2 item = (ItemBean2) items.get(Integer.valueOf(bean.getCode()));
		if (item == null) {
			bean.setQuantity(nums);
			items.put(Integer.valueOf(bean.getCode()), bean);
		} else {
			item.setQuantity(nums + item.getQuantity());
		}
		recalcTotal();
	}

	public void deleteCart(int itemCode) {
		items.remove(Integer.valueOf(itemCode));
		recalcTotal();
	}

	public int getTotal() {
		return total;
	}

	private void recalcTotal() {
		total = 0;
		Collection<ItemBean2> list = items.values();
		for (ItemBean2 item : list) {
			total += item.getPrice() * item.getQuantity();
		}
	}
}
