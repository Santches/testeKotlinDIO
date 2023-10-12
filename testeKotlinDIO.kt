enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(val nome: String, val curso: String)

data class ConteudoEducacional(var nome: String, var duracao: Int = 0, var vagas: Int = 0)

var usuariosMatriculados = 0;

data class Formacao(val nome: String, var nivel: Nivel, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario, conteudo: ConteudoEducacional): Boolean {
        if (usuariosMatriculados < conteudo.vagas) {
            usuariosMatriculados++
            println("${usuario.nome}, matrícula realizada com sucesso no curso $nome.")
            return true
        } else {
            println("Desculpe, $(usuario.nome). As vagas para o curso $nome estão esgotadas.")
            return false
        }
    }
}

fun main() {

    val listaDeInscritos = mutableListOf(
        Usuario("João", "Curso de Kotlin"),
        Usuario("Maria", "Curso de Kotlin"),
        Usuario("José", "Curso de Design Gráfico")
    )

    val conteudo1 = ConteudoEducacional("Introdução ao Kotlin", 60, 3)
    val conteudo2 = ConteudoEducacional("Programação Orientada a Objetos", 90, 3)

    val formacao1 = Formacao("Curso de Kotlin", Nivel.INTERMEDIARIO, listOf(conteudo1, conteudo2))
    val formacao2 = Formacao("Curso de Design Gráfico", Nivel.DIFICIL, listOf(conteudo1, conteudo2))

    while (true) {
        println("\n### Sistema de Matrícula ###")
        println("1 - Realizar Matrícula")
        println("2 - Verificar Matrícula")
        println("3 - Listar Usuários Matriculados")
        println("4 - Sair")
        println("\nEscolha uma opção: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("Digite o nome do usuário: ")
                val nome = readLine() ?: ""

                while (true) {
                    println("\nSelecione o Curso:")
                    println("1 - Curso de Kotlin")
                    println("2 - Curso de Design Gráfico")
                    println("3 - Voltar pra tela anterior")

                    when (readLine()?.toIntOrNull()) {
                        1 -> {
                            val curso = "Curso Kotlin"
                            val novoUsuario = Usuario(nome, curso)
                            listaDeInscritos.add(novoUsuario)
                            println("Parabéns, ${novoUsuario.nome}! Sua matrícula foi realizada com sucesso no curso: ${formacao1.nome}!")
                        }

                        2 -> {
                            val curso = "Curso de Design Gráfico"
                            val novoUsuario = Usuario(nome, curso)
                            listaDeInscritos.add(novoUsuario)
                            println("Parabéns, ${novoUsuario.nome}! Sua matrícula foi realizada com sucesso no curso: ${formacao2.nome}!")
                        }

                        3 -> {
                            break
                        }
                    }
                }

            }

            2 -> {
                println("Digite o nome do Usuário: ")
                val nomeUsuario = readLine()?.toString()

                val usuarioEncontrado = listaDeInscritos.find { it.nome == nomeUsuario }

                if (usuarioEncontrado != null) {
                    println("\nMatrícula encontrada para ${usuarioEncontrado.nome}, no curso: ${usuarioEncontrado.curso}.")
                } else {
                    println("\nNenhum usuário encontrado para $nomeUsuario.")
                }
            }

            3 -> {
                println("### Lista de Usuários Matriculados ###")
                listaDeInscritos.forEach{usuario ->  println("Nome: ${usuario.nome}, Curso: ${usuario.curso}")}
            }
            4 -> {
                println("Encerrando Sistema. Até breve!")
                break
            } else -> {
                println("Opção inválida. Por favor, insira uma opção válida.")
            }
        }
    }

}
