package util;

import java.util.List;

import model.DapAn;

public class ToString {
	public static String toString(List<DapAn> list){
		StringBuilder sb = new StringBuilder();
		int k = 1;
		for (DapAn dapAn : list) {
			sb.append(k++);
			sb.append(". ");
			sb.append(dapAn.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	public static String toStringImage(List<String> list){
		StringBuilder sb = new StringBuilder();
		String format = "<a href='download?fileName=%s' target='_blank'>%s</a><br/>";
		for (String s : list) {
			sb.append(String.format(format, s,s));
		}
		return sb.toString();
	}
}
