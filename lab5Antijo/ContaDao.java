import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ContaDao implements IContaDao {
    private PreparedStatement pstmCreate;
    private PreparedStatement pstmRead;
    private PreparedStatement pstmReadByNumber;
    private PreparedStatement pstmUpdate;
    private PreparedStatement pstmDelete;



    public ContaDao(Connection c) throws Exception {
        pstmCreate = c.prepareStatement("INSERT INTO CONTAS VALUES (?, ?)");
        pstmRead = c.prepareStatement("SELECT * FROM CONTAS");
        pstmReadByNumber = c.prepareStatement("SELECT * FROM CONTAS WHERE NRO_CONTA = ?");
        pstmUpdate = c.prepareStatement("UPDATE CONTAS SET SALDO = ? WHERE NRO_CONTA = ?");
        pstmDelete = c.prepareStatement("DELETE FROM CONTAS WHERE NRO_CONTA = ?");
    }
    
    @Override
    public boolean criar(Conta c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'criar'");
    }

    @Override
    public List<Conta> lerTodas() throws Exception {
        List<Conta> contas = new ArrayList<>();
        ResultSet resultados; 
        resultados = pstmRead.executeQuery();
        while(resultados.next()){
            long n = resultados.getLong("nro_conta");
            BigDecimal s = resultados.getBigDecimal("saldo");
            Conta c = new Conta(n,s);
            contas.add(c);
        }
        return contas;
    }
    
    @Override
    public Conta buscarPeloNumero(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPeloNumero'");
    }

    @Override
    public boolean atualizar(Conta c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public boolean apagar(Conta c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apagar'");
    }
    
}