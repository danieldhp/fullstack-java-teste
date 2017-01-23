(function () {
    'use strict';
    var controllerId = 'consultaImpostosController';
    angular.module('TesteContabilizei.consultaImpostos').controller(controllerId, controller);

    controller.$inject = ['servicesFactory'];

    function controller(servicesFactory) {

        var vm = this;

        vm.listarImpostos = listarImpostos;
        vm.getClientes = getClientes;
        vm.labelTipoImposto = labelTipoImposto;
        vm.alterarStatusImposto = alterarStatusImposto;

        (function init() {
            vm.filtro = {};
            vm.listaImpostos = [];

            // listarImpostos();
        })();

        function listarImpostos() {
            servicesFactory.consultaImpostos.listarCalculosImpostos(vm.filtro).then(retornoListarImpostos);
        }

        function retornoListarImpostos(data) {
            vm.listaImpostos = data;
        }

        function getClientes(pesquisa) {
            var filtro = {pesquisa: pesquisa};

            return servicesFactory.cliente.listarClientes(filtro).then(function (res) {
                if (!res || res.length === 0) {
                    res = [];
                    var item = {nome: "Nenhum cliente encontrado com esse nome", id: 0};
                    res.push(item);
                }
                return res;
            });
        }

        function labelTipoImposto(imposto) {
            if (imposto.tipoImposto === 'SN') {
                return "Simples nacional";
            } else {
                return imposto.tipoImposto;
            }
        }

        function alterarStatusImposto(imposto) {
            servicesFactory.consultaImpostos.alterarStatusImposto(imposto).then(retornoAlterarStatusImposto);
        }

        function retornoAlterarStatusImposto(data) {
            for (var i = 0; i < vm.listaImpostos.length; i++) {
                if (vm.listaImpostos[i].id === data.objeto.id) {
                    vm.listaImpostos[i] = data.objeto;
                    break;
                }
            }
        }

        return vm;
    }
})();