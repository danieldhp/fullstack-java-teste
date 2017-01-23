(function () {
    'use strict';

    angular.module('TesteContabilizei', [
        'ui.router',
        'ui.bootstrap',
        'ui.mask',

        'TesteContabilizei.util',
        'TesteContabilizei.componentes',
        'TesteContabilizei.cliente',
        'TesteContabilizei.notaFiscal',
        'TesteContabilizei.calculoImpostos',
        'TesteContabilizei.consultaImpostos'
    ]);

})();