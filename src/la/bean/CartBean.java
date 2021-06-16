package la.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CartBean {
	private Map<Integer, ShopItemBean> items = new HashMap<Integer, ShopItemBean>();
	private int total;

	public CartBean() {
	}

	public Map<Integer, ShopItemBean> getItems() {
		return items;
	}

	public void addCart(ShopItemBean bean, int nums) {
		ShopItemBean item = (ShopItemBean) items.get(Integer.valueOf(bean.getCode()));
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
		Collection<ShopItemBean> list = items.values();
		for (ShopItemBean item : list) {
			total += item.getPrice() * item.getQuantity();
		}
	}
}