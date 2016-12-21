$(document).ready(function(){
    Morris.Area({
        element: 'morris-area-chart',
        data: [{
            period: '2010 Q1',
            Sale: 2666
           
        }, {
            period: '2011 Q1',
            Sale: 6810
           
           
        }],
        xkey: 'period',
        ykeys: ['iphone'],
        labels: ['iPhone'],
        pointSize: 2,
        hideHover: 'auto',
        resize: true
    });
});
