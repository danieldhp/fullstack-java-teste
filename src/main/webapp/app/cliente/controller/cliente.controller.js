(function () {
    'use strict';
    var controllerId = 'clienteController';
    angular.module('TesteContabilizei.cliente').controller(controllerId, controller);

    controller.$inject = ['servicesFactory'];

    function controller(servicesFactory) {

        var vm = this;

        vm.novoCliente = novoCliente;
        vm.editarCliente = editarCliente;
        vm.salvarCliente = salvarCliente;
        vm.listarClientes = listarClientes;
        vm.voltar = voltar;
        vm.labelRegimeTributario = labelRegimeTributario;

        (function init() {
            vm.filtro = {};
            vm.listaClientes = [];

            vm.manutencao = false;
            vm.lista = true;

            listarClientes();
        })();

        function novoCliente() {
            vm.cliente = {};

            vm.manutencao = true;
            vm.lista = false;
        }

        function editarCliente(cliente) {
            vm.cliente = cliente;

            vm.manutencao = true;
            vm.lista = false;
        }

        function listarClientes() {
            servicesFactory.cliente.listarClientes(vm.filtro).then(retornoListarClientes);
        }

        function retornoListarClientes(data) {
            vm.listaClientes = data;
        }

        function salvarCliente() {
            vm.alertas = [];
            if (vm.frmCliente.$invalid) {
                vm.alertas = [{tipo: 'danger', mensagem: 'Preencha os campos obrigatorios!'}];
                return;
            } else if (!servicesFactory.util.validarCnpj(vm.cliente.cnpj)) {
                vm.alertas = [{tipo: 'danger', mensagem: 'Preencha um CNPJ valido!'}];
                return;
            }

            servicesFactory.cliente.salvarCliente(vm.cliente).then(retornoSalvarCliente);
        }

        function retornoSalvarCliente(data) {
            vm.alertas = [{tipo: data.tipo, mensagem: data.mensagem}];

            var editou = false;
            for (var i = 0; i < vm.listaClientes.length; i++) {
                if (data.objeto.id === vm.listaClientes[i].id) {
                    vm.listaClientes[i] = data.objeto;
                    editou = true;
                    break;
                }
            }
            if (!editou) {
                vm.listaClientes.push(data.objeto);
            }

            voltar();
        }

        function labelRegimeTributario(cliente) {
            if (cliente.regimeTributario === 'SN') {
                return "Simples nacional";
            } else if (cliente.regimeTributario === 'LP') {
                return "Lucro presumido";
            }
            return '';
        }

        function voltar() {
            vm.cliente = {};
            vm.manutencao = false;
            vm.lista = true;
        }

        return vm;
    }
})();