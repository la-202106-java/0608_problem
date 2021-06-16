package la.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CartBean {
	private Map<Integer, ItemBean7> items = new HashMap<Integer, ItemBean7>();
	private int total;

	public CartBean() {
	}

	public Map<Integer, ItemBean7> getItems() {
		return items;
	}

	public void addCart(ItemBean7 bean, int nums) {
		ItemBean7 item = (ItemBean7) items.get(Integer.valueOf(bean.getCode()));
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
		Collection<ItemBean7> list = items.values();
		for (ItemBean7 item : list) {
			total += item.getPrice() * item.getQuantity();
		}
	}
}