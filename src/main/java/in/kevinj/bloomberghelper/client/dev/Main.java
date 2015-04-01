package in.kevinj.bloomberghelper.client.dev;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Main {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		/*InputStream stream = new FileInputStream("");
		Client client = ApacheHttpClient.create();
		client.setChunkedEncodingSize(1024);
		client.asyncResource("http://localhost:25274/bloomberghelper/TERM_CLIENT/register")
				.type(MediaType.TEXT_PLAIN)
				.post(new TypeListener<String>(String.class) {
					public void onComplete(Future<String> f) throws InterruptedException {
						try {
							System.out.println(f.get());
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
					}
				}, stream)
		;
		/*ClientBuilder.newClient().target("http://localhost:25274/bloomberghelper/").path("/{role}/register").resolveTemplate("role", "TERM_CLIENT").request().header("Connection", "close").async().post(stream, new InvocationCallback<String>() {
			public void completed(String response) {
				System.out.println("RESPONSE");
			}

			public void failed(Throwable throwable) {
				throwable.printStackTrace();
			}
		});*/

		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			HttpPost httppost = new HttpPost("http://localhost:25274/bloomberghelper/DEV_CLIENT/register");
			httppost.setEntity(new StringEntity("TEST"));
			System.out.println("Executing request: " + httppost.getRequestLine());
			try (CloseableHttpResponse response = httpclient.execute(httppost)) {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				Charset charset;
				if (ContentType.get(response.getEntity()) != null && ContentType.get(response.getEntity()).getCharset() != null)
					charset = ContentType.get(response.getEntity()).getCharset();
				else
					charset = Charset.defaultCharset();
				byte[] buffer = new byte[4096];
				try (InputStream resp = response.getEntity().getContent()) {
					int read;
					while ((read = resp.read(buffer)) != -1)
						System.out.print(new String(buffer, 0, read, charset));
				}
			}
		}
	}
}
