const { createApp } = Vue
const urlBuscaLivros = "http://localhost:8080/livro"
const urlBuscaReservados = "http://localhost:8080/livro/reservados"
const urlReserva = "http://localhost:8080/reserva"
const mainContainer = {
    data() {
        return {
            livros: [],
            formLivro: {
                isNew: true,
                nome: '',
                autores: '',
                editora: '',
                genero: '',
                idioma: '',
                paginas: '',
                titulo: 'Cadastrar livro',
                botao: 'Cadastrar'
            },
            reservas: []
        }
    },
    mounted() {
        this.getLivros(),
        this.getReservas()
    },
    methods: {
        getLivros() {
            axios.get(urlBuscaLivros).then(response => {
                response.data.forEach(item => {
                    this.livros.push(item)
                })
            })
        },
        salvarLivro() {
            this.livros = []
            this.formLivro.nome = this.formLivro.nome
            this.formLivro.autores = this.formLivro.autores
            this.formLivro.editora = this.formLivro.editora
            this.formLivro.genero = this.formLivro.genero
            this.formLivro.idioma = this.formLivro.idioma
            this.formLivro.paginas = this.formLivro.paginas

            if(this.formLivro.nome == '') {
                toastr.error('O campo nome é obrigatório!', 'Formulário')
                return
            }

            const livro = {
                nome: this.formLivro.nome,
                autores: this.formLivro.autores,
                editora: this.formLivro.editora,
                genero: this.formLivro.genero,
                idioma: this.formLivro.idioma,
                paginas: this.formLivro.paginas
            }

            const self = this

            axios.post(urlBuscaLivros, livro)
                .then(function(response) {
                    toastr.success('Livro cadastrado com sucesso!', 'Formulário')
                })
                .catch(function(error) {
                    toastr.error('Não foi possível cadastrar livro!', 'Formulário')
                })
                .then(function() {
                    self.getLivros()
                    self.limparFormulario()
                })
        },
        limparFormulario() {
            this.formLivro.isNew = true
            this.formLivro.nome = ''
            this.formLivro.autores = ''
            this.formLivro.editora = ''
            this.formLivro.genero = ''
            this.formLivro.idioma = ''
            this.formLivro.paginas = ''
            this.formLivro.titulo = 'Cadastrar livro'
            this.formLivro.botao = 'Cadastrar'
        },
        getReservas() {
            axios.get(urlBuscaReservados).then(response => {
                response.data.forEach(item => {
                    this.reservas.push({
                        nome: item.nome,
                        dataReserva: this.formatarData(item.dataReserva),
                        dataDevolucao: this.formatarData(item.dataDevolucao)
                    })
                })
            })
        },
        formatarData(dataParaSerFormatada) {
            return (new Date(dataParaSerFormatada.split('T')[0])).toLocaleDateString("pt-br")
        },
        buscaNomeBotao(obj) {
            if(obj == null)
                return "Reservar"
            else
                return "Reservado"
        },
        reservarLivro(obj) {
            console.log(this.livros)
            axios.post(urlReserva, {livros:[{id:2}]}, {
                headers: {
                    'content-type': 'application/json'
                }
            }).then(response => {
                this.setState({data:response.data});
                console.log('nati'+this.state.data);
            })
        }
    }
}

createApp(mainContainer).mount('#app')