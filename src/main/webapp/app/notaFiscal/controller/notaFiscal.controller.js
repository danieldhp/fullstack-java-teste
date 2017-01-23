(function () {
    'use strict';
    var controllerId = 'notaFiscalController';
    angular.module('TesteContabilizei.notaFiscal').controller(controllerId, controller);

    controller.$inject = ['servicesFactory'];

    function controller(servicesFactory) {

        var vm = this;

        vm.novaNotaFiscal = novaNotaFiscal;
        vm.editarNotaFiscal = editarNotaFiscal;
        vm.salvarNotaFiscal = salvarNotaFiscal;
        vm.listarNotasFiscais = listarNotasFiscais;
        vm.voltar = voltar;
        vm.labelRegimeTributario = labelRegimeTributario;
        vm.getClientes = getClientes;
        vm.verificaClienteSelecionado = verificaClienteSelecionado;

        (function init() {
            vm.filtro = {};
            vm.listaNotasFiscais = [];

            vm.datepicker = {opened: false};

            vm.manutencao = false;
            vm.lista = true;

            listarNotasFiscais();
        })();

        function novaNotaFiscal() {
            vm.notaFiscal = {};

            vm.manutencao = true;
            vm.lista = false;
        }

        function editarNotaFiscal(notaFiscal) {
            vm.notaFiscal = notaFiscal;
            vm.notaFiscal.data = new Date(notaFiscal.data);

            vm.manutencao = true;
            vm.lista = false;
        }

        function listarNotasFiscais() {
            servicesFactory.notaFiscal.listarNotasFiscais(vm.filtro).then(retornoListarNotasFiscais);
        }

        function retornoListarNotasFiscais(data) {
            vm.listaNotasFiscais = data;
        }

        function salvarNotaFiscal() {
            vm.alertas = [];
            if (vm.frmNotaFiscal.$invalid) {
                vm.alertas = [{tipo: 'danger', mensagem: 'Preencha os campos obrigatorios!'}];
                return;
            } else if (!vm.notaFiscal.valor) {
                vm.alertas = [{tipo: 'danger', mensagem: 'Preencha os campos obrigatorios!'}];
                return;
            }

            if (vm.notaFiscal.valor.replace) {
                vm.notaFiscal.valor = vm.notaFiscal.valor.replace(/,/g, '.');
            }

            servicesFactory.notaFiscal.salvarNotaFiscal(vm.notaFiscal).then(retornoSalvarNotaFiscal);
        }

        function retornoSalvarNotaFiscal(data) {
            vm.alertas = [{tipo: data.tipo, mensagem: data.mensagem}];

            var editou = false;
            for (var i = 0; i < vm.listaNotasFiscais.length; i++) {
                if (data.objeto.id === vm.listaNotasFiscais[i].id) {
                    vm.listaNotasFiscais[i] = data.objeto;
                    editou = true;
                    break;
                }
            }
            if (!editou) {
                vm.listaNotasFiscais.push(data.objeto);
            }

            voltar();
        }

        function labelRegimeTributario(notaFiscal) {
            if (notaFiscal.regimeTributario === 'SN') {
                return "Simples nacional";
            } else if (notaFiscal.regimeTributario === 'LP') {
                return "Lucro presumido";
            }
            return '';
        }

        function voltar() {
            vm.notaFiscal = {};
            vm.manutencao = false;
            vm.lista = true;
        }

        function getClientes(pesquisa) {
            var filtro = {pesquisa: pesquisa};

            return servicesFactory.cliente.listarClientes(filtro).then(function (res) {
                if (!res || res.length === 0) {
                    res = [];
                    var item = {razaoSocial: "Nenhum cliente encontrado com esse nome", id: 0};
                    res.push(item);
                }
                return res;
            });
        }

        function verificaClienteSelecionado() {
            if (vm.notaFiscal.cliente.id === 0) {
                vm.notaFiscal.cliente = null;
            }
        }

        return vm;
    }
})();