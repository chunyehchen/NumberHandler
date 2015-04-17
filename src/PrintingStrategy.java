import java.io.Serializable;


public interface PrintingStrategy extends Serializable {
	public void print(int num);
}
