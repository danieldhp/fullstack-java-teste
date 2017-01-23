/**
 * Recupera todos os services do sistema.
 * Assim ao injetar o servicesFactory
 * não precisará injetar nenhum outro service para não deixar grande as importações
 */
(function () {
    'use strict';
    var factoryId = "servicesFactory";
    angular.module('TesteContabilizei.util').factory(factoryId, servicesFactory);

    servicesFactory.$inject = ['utilService', 'clienteService', 'notaFiscalService', 'calculoImpostosService',
        'consultaImpostosService'];

    function servicesFactory(utilService, clienteService, notaFiscalService, calculoImpostosService,
                             consultaImpostosService) {

        var service = {
            cliente: clienteService,
            util: utilService,
            notaFiscal: notaFiscalService,
            calculoImpostos: calculoImpostosService,
            consultaImpostos: consultaImpostosService
        };

        return service;
    }

})();