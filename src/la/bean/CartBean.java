package la.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CartBean {
	private Map<Integer, SampleItemBean> items = new HashMap<Integer, SampleItemBean>();
	private int total;

	public CartBean() {
	}

	public Map<Integer, SampleItemBean> getItems() {
		return items;
	}

	public void addCart(SampleItemBean bean, int nums) {
		SampleItemBean item = (SampleItemBean) items.get(Integer.valueOf(bean.getCode()));
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
		Collection<SampleItemBean> list = items.values();
		for (SampleItemBean item : list) {
			total += item.getPrice() * item.getQuantity();
		}
	}
}