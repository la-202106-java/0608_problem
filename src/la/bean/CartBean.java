package la.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CartBean {
	private Map<Integer, ShoppingItemBean> items = new HashMap<Integer, ShoppingItemBean>();
	private int total;

	public CartBean() {
	}

	public Map<Integer, ShoppingItemBean> getItems() {
		return items;
	}

	public void addCart(ShoppingItemBean bean, int nums) {
		ShoppingItemBean item = (ShoppingItemBean) items.get(Integer.valueOf(bean.getCode()));
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
		Collection<ShoppingItemBean> list = items.values();
		for (ShoppingItemBean item : list) {
			total += item.getPrice() * item.getQuantity();
		}
	}
}