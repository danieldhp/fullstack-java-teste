(function () {
    'use strict';
    var serviceId = 'calculoImpostosService';
    angular.module('TesteContabilizei.calculoImpostos').service(serviceId, service);

    service.$inject = ['utilService'];
    function service(utilService) {

        var service = {
            calcularImpostos: calcularImpostos
        };

        return service;

        function calcularImpostos(informacoesCalculo) {
            return utilService.post('testeContabilizeiService/calculoImpostos/calcularImpostos', informacoesCalculo);
        }
    }
})();