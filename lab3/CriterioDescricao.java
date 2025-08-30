public class CriterioDescricao implements CriterioBusca {
    public boolean testar(Produto p, String valor) {

        String descricaoMinuscula = p.getDescricao().toLowerCase();
        return descricaoMinuscula.contains(valor.toLowerCase());

    }
}
