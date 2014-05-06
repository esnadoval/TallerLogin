define(['controller/_carroComprasController', 'delegate/carroComprasDelegate'], function() {
    App.Controller.CarroComprasController = App.Controller._CarroComprasController.extend({
        /*El método list que se encarga de mostrar la lista de carritos de compras se reimplementa 
         * para que solo se muestren los carritos de compras del usuario autenticado*/
        list: function(params) {
            //Inicialización estandar del modelo
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' + 'instead-carroCompras-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carroCompras-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carroCompras-list', {view: this, data: data});
                var self = this;
                if (!this.carroComprasModelList) {
                    this.carroComprasModelList = new this.listModelClass();
                }

//Lamada al delegate para obtener el nombre del usuario autenticado
                App.Delegate.CarroComprasDelegate.prototype.getLogedUser("", function(data) {
                    //Si la llamada es exitosa, se obtienen los carritos de compras según el nombre obtenido anteriormente
                    //haciendo la llamada al delegate hacia el nuevo servicio del carrito de compras, que obtiene lo carritos de compras de un usuario
                    App.Delegate.CarroComprasDelegate.prototype.getClientCarts(data, function(data2) {


                        var elementos = data2;
                        //se crea un nuevo modelList con la respuesta del servicio
                        self.carroComprasModelList = new self.listModelClass();
                        _.each(elementos, function(d) {
                            var model = new App.Model.CarroComprasModel(d);

                            self.carroComprasModelList.models.push(model);

                        });
                        //La lista se renderiza con los elementos obtenidos
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-carroCompras-list', {view: self});


                    }, function(data3) {

                    });

                }, function(data4) {


                });

            }
        },
        /*Se reimplmenta la función _loadRequiredComponentsData para que el combo box de 
         * 'createCarroCompras' solo muestre la opción que hacer referencia al usuario autenticado*/
        _loadRequiredComponentsData: function(callBack) {
            var self = this;
            var listReady = _.after(1, function() {
                callBack();
            });
            var listDataReady = function(componentName, model) {
               //Lamado al delegate para saber el usuario actualmente autenticado
                App.Delegate.CarroComprasDelegate.prototype.getLogedUser("", function(data) {
                    var list = model.models;
                    //el modellist (en la variable model) se reconstruye eliminando los demás clientes.
                    for (var i = 0; i < list.length; i++) {
                        if (list[i].attributes.name != data) {
                            list.splice(i, 1);
                        }
                    }
                    self[componentName] = model;
                    listReady();

                }, function(data4) {


                });

            };
            App.Utils.getComponentList('clienteComponent', listDataReady);
        }
    });
    return App.Controller.CarroComprasController;
}); 