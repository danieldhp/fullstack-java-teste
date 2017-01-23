(function () {
    'use strict';
    var serviceId = 'notaFiscalService';
    angular.module('TesteContabilizei.notaFiscal').service(serviceId, service);

    service.$inject = ['utilService'];
    function service(utilService) {

        var service = {
            listarNotasFiscais: listarNotasFiscais,
            salvarNotaFiscal: salvarNotaFiscal
        };

        return service;

        function listarNotasFiscais() {
            return utilService.post('testeContabilizeiService/notaFiscal/listarNotasFiscais');
        }

        function salvarNotaFiscal(notaFiscal) {
            return utilService.post('testeContabilizeiService/notaFiscal/salvarNotaFiscal', notaFiscal);
        }
    }
})();