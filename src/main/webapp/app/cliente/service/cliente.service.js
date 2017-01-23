(function () {
    'use strict';
    var serviceId = 'clienteService';
    angular.module('TesteContabilizei.cliente').service(serviceId, service);

    service.$inject = ['utilService'];
    function service(utilService) {

        var service = {
            listarClientes: listarClientes,
            salvarCliente: salvarCliente
        };

        return service;

        function listarClientes(filtro) {
            return utilService.post('testeContabilizeiService/cliente/listarClientes', filtro);
        }

        function salvarCliente(cliente) {
            return utilService.post('testeContabilizeiService/cliente/salvarCliente', cliente);
        }
    }
})();