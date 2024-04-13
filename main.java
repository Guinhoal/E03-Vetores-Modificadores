import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("João");

        Conta conta1 = new Conta(cliente1, 12345, "Agencia A", 1000);

        conta1.depositar(1000);
        conta1.sacar(500);

        conta1.imprimirExtrato();
    }
}

class Cliente {
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

class Conta {
    private Cliente dono;
    private double saldo;
    private int numero;
    private String agencia;
    private double limite;
    private ArrayList<Operacao> operacoes;
    private int ultimaPosicao;

    public Conta(Cliente dono, int numero, String agencia, double limite) {
        this.dono = dono;
        this.numero = numero;
        this.agencia = agencia;
        this.limite = limite;
        this.saldo = 0.0;
        this.operacoes = new ArrayList<>();
        this.ultimaPosicao = 0;
    }

    public boolean depositar(double valor) {
        if (valor > 0.0) {
            this.saldo += valor;
            adicionarOperacao('d', valor);
            return true;
        } else {
            return false;
        }
    }

    public boolean sacar(double valor) {
        if (valor > 0.0 && valor <= (this.saldo + this.limite)) {
            this.saldo -= valor;
            adicionarOperacao('s', valor);
            return true;
        } else {
            return false;
        }
    }

    public void imprimirExtrato() {
        System.out.println("Extrato da Conta:");
        for (Operacao op : operacoes) {
            System.out.println("Tipo: " + op.getTipo() + ", Valor: " + op.getValor() + ", Data: " + op.getDataOperacao());
        }
        System.out.println("Saldo atual: " + saldo);
    }

    private void adicionarOperacao(char tipo, double valor) {
        if (ultimaPosicao < 1000) {
            operacoes.add(new Operacao(tipo, valor));
            ultimaPosicao++;
        } else {
            System.out.println("Limite de operações atingido. Não é possível adicionar mais operações.");
        }
    }
}

class Operacao {
    private char tipo;
    private double valor;
    private Date dataOperacao;

    public Operacao(char tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataOperacao = new Date();
    }

    public char getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public Date getDataOperacao() {
        return dataOperacao;
    }
}
