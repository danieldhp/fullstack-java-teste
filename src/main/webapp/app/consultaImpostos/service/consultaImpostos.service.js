(function () {
    'use strict';
    var serviceId = 'consultaImpostosService';
    angular.module('TesteContabilizei.consultaImpostos').service(serviceId, service);

    service.$inject = ['utilService'];
    function service(utilService) {

        var service = {
            listarCalculosImpostos: listarCalculosImpostos,
            alterarStatusImposto: alterarStatusImposto
        };

        return service;

        function listarCalculosImpostos(informacoesCalculo) {
            return utilService.post('testeContabilizeiService/calculoImpostos/listarCalculosImpostos', informacoesCalculo);
        }

        function alterarStatusImposto(calculoImposto) {
            return utilService.post('testeContabilizeiService/calculoImpostos/alterarStatusImposto', calculoImposto);
        }
    }
})();