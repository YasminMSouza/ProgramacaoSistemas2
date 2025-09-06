
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;


public class laboratorio04 {

    public static void main(String[] args) throws Exception {

        System.out.println("Consulta de contas!");
        //read();  
        //create();
        //update();
        //delete();

        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.ujlvzuflyvzlmxvdnlrz&password=Lab0403";
        Connection c = DriverManager.getConnection(url);
        System.out.println("Conexão ok!");

        String sql = "SELECT * FROM CONTAS";
        PreparedStatement stm = c.prepareStatement(sql);
        ResultSet resultado = stm.executeQuery();
        
        while (resultado.next()) {
            long nro = resultado.getLong("nro_conta");
            double saldo = resultado.getDouble("saldo");
            System.out.println("Número: " + nro + " - R$ " + saldo);
        }
        c.close();
    }
    //create
    public static void create() throws SQLException {
        System.out.print("Número para a nova conta: ");
        long nro = Long.parseLong(System.console().readLine());
        System.out.print("Saldo da nova conta: ");
        BigDecimal saldo = new BigDecimal(System.console().readLine());

        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.ujlvzuflyvzlmxvdnlrz&password=Lab0403";
        Connection c = DriverManager.getConnection(url);

        String sql = "INSERT INTO contas (nro_conta, saldo) VALUES (?,?)";
        PreparedStatement prepstm = c.prepareStatement(sql);
        prepstm.setLong(1, nro);
        prepstm.setBigDecimal(2, saldo);
        int ret = prepstm.executeUpdate();
        System.out.println("Número de registros inseridos: " + ret);
        c.close();
    }

    //read
    public static void read() throws SQLException {

        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.ujlvzuflyvzlmxvdnlrz&password=Lab0403";
        Connection c = DriverManager.getConnection(url);

        String sql = "SELECT * FROM contas";
        PreparedStatement stm = c.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();

        while (rs.next()) {
            long nro = rs.getLong("nro_conta");
            BigDecimal saldo = rs.getBigDecimal("saldo");
            System.out.println("Conta número: " + nro + " tem saldo de R$ " + saldo);
        }
        c.close();
    }

    //update
    public static void update() throws SQLException {
        System.out.print("Número de uma conta já existente: ");
        long nro = Long.parseLong(System.console().readLine());
        System.out.print("Novo saldo para esta conta: ");
        BigDecimal saldo = new BigDecimal(System.console().readLine());

        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.ujlvzuflyvzlmxvdnlrz&password=Lab0403";
        Connection c = DriverManager.getConnection(url);

        String sql = "UPDATE contas SET saldo = ? WHERE nro_conta = ?";
        PreparedStatement prepstm = c.prepareStatement(sql);
        prepstm.setBigDecimal(1, saldo);
        prepstm.setLong(2, nro);
        int ret = prepstm.executeUpdate();
        System.out.println("Número de registros alterados: " + ret);
        c.close();
    }

    //delete
    public static void delete() throws SQLException {
        System.out.print("Número de uma conta já existente: ");
        long nro = Long.parseLong(System.console().readLine());

        String url = "jdbc:postgresql://aws-1-sa-east-1.pooler.supabase.com:6543/postgres?user=postgres.ujlvzuflyvzlmxvdnlrz&password=Lab0403";
        Connection c = DriverManager.getConnection(url);

        String sql = "DELETE FROM contas WHERE nro_conta = ?";
        PreparedStatement prepstm = c.prepareStatement(sql);
        prepstm.setLong(1, nro);
        int ret = prepstm.executeUpdate();
        System.out.println("Número de registros apagados: " + ret);
        c.close();
    }
}

