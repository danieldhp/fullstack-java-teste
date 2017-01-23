(function () {
    'use strict';
    var controllerId = 'calculoImpostosController';
    angular.module('TesteContabilizei.calculoImpostos').controller(controllerId, controller);

    controller.$inject = ['servicesFactory'];

    function controller(servicesFactory) {

        var vm = this;

        vm.calcularImpostos = calcularImpostos;
        vm.getClientes = getClientes;
        vm.novoCalculo = novoCalculo;
        vm.verificaClienteSelecionado = verificaClienteSelecionado;

        function calcularImpostos() {
            vm.alertas = [];
            if (vm.frmCalculoImpostos.$invalid) {
                vm.alertas = [{tipo: 'danger', mensagem: 'Preencha os campos obrigatorios!'}];
                return;
            }
            servicesFactory.calculoImpostos.calcularImpostos(vm.informacoesCalculo).then(retornoCalcularImpostos);
        }

        function retornoCalcularImpostos(data) {
            vm.alertas = [{tipo: data.tipo, mensagem: data.mensagem}];
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
            if (vm.informacoesCalculo.cliente.id === 0) {
                vm.informacoesCalculo.cliente = null;
            }
        }

        function novoCalculo() {
            vm.alertas = [];
            vm.informacoesCalculo = {};
        }

        return vm;
    }
})();