import redis.clients.jedis.Jedis;

public class AppTest {
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		String res = jedis.set("name", "沈苗");
		jedis.expire("name", 30);
		System.out.println(res);
		String value = jedis.get("name");
		System.out.println(value);
	}
}
