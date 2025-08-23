import java.util.ArrayList;
import java.util.List;

public class Serie extends Midia {
    private List<Temporada> temporadas;

    public Serie(String titulo) {
        super(titulo);
        this.temporadas = new ArrayList<>();
    }

    public void adicionar(Temporada temporada) {
        temporadas.add(temporada);
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    @Override
    public long getDuracao() {
        long total = 0;
        for (Temporada t : temporadas) {
            total += t.getDuracao();
        }
        return total;
    }

    @Override
    public String info() {
        return "Série: " + super.info()
                + "; Temporadas: " + temporadas.size()
                + "; Duração total: " + getDuracao();
    }
}
