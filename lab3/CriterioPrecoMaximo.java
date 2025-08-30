public class CriterioPrecoMaximo implements CriterioBusca {

    @Override
    public boolean testar(Produto p, String valor) {
        try {
            double preco = Double.parseDouble(valor);
            return p.getPreco() <= preco;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean testarPrecoMinimo(Produto p, String valor) {
        try {
            double preco = Double.parseDouble(valor);
            return p.getPreco() >= preco;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}