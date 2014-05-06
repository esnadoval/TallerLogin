define([], function() {
    App.Delegate.LoginDelegate = {
        //Función que obtiene el "user Stack" del servicio rest.
        getLogedUserStack: function(sdata, callback, callbackError) {
            $.ajax({
                url: '/carrocompras.master.service.subsystem-0.0.1-SNAPSHOT/webresources/auth/session/userstack',
                method: 'GET',
                dataType: "html",
                contentType: "application/json"
            }).done(function(data) {
                callback(data);
            }).fail(function(error) {
                callbackError(error);
            });
        }
        
    };
 
});

