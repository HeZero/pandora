import java.util.ServiceLoader;

public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<DogService> loaders = ServiceLoader.load(DogService.class);
        for(DogService d: loaders)
        {
            d.sleep();
        }
    }
}

interface DogService{
    void sleep();
}

class BlackDogService implements DogService
{
    @Override
    public void sleep() {
        System.out.println("黑色的狗在睡觉");
    }
}

class WhiteDogService implements DogService
{
    @Override
    public void sleep() {
        System.out.println("白色的狗在睡觉");
    }
}

