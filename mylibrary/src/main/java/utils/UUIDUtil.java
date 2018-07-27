package utils;

import java.util.UUID;

/**
 * UUID的生成类
 * 
 * @author Yang.Cheng
 * @date 2013-2-21上午10:31:19
 * @Copyright(c) Chengdu Chant Software Technology Co.,LTD.
 */
public final class UUIDUtil {

	private UUIDUtil() {

	}

	/**
	 * TC系统中采用long型的UUID 取UUID的高64位，和时间强相关，保证本单位唯一
	 * 
	 * @return 获取一个随机long类型的id号
	 */
	public static long UUIDLong() {
		return UUIDAbsLong();
	}

	/**
	 * 获取正值的UUID 获取主键时不要使用
	 * 
	 * @deprecated
	 * @return
	 */
	private static long UUIDAbsLong() {
		while (true) {
			long r = UUID.randomUUID().getMostSignificantBits();
			if (r > 0) {
				return r;
			}
		}
	}
}
