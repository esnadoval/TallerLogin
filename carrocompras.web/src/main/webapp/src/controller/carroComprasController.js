define(['controller/_carroComprasController', 'delegate/carroComprasDelegate'], function() {
    App.Controller.CarroComprasController = App.Controller._CarroComprasController.extend({
        list: function(params) {

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


                App.Delegate.CarroComprasDelegate.prototype.getLogedUser("", function(data) {

                    App.Delegate.CarroComprasDelegate.prototype.getClientCarts(data, function(data2) {


                        var elementos = data2;

                        self.carroComprasModelList = new self.listModelClass();
                        _.each(elementos, function(d) {
                            var model = new App.Model.CarroComprasModel(d);

                            self.carroComprasModelList.models.push(model);

                        });

                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-carroCompras-list', {view: self});


                    }, function(data3) {

                    });

                }, function(data4) {


                });

            }
        },
        _loadRequiredComponentsData: function(callBack) {
            var self = this;
            var listReady = _.after(1, function() {
                callBack();
            });
            var listDataReady = function(componentName, model) {
                App.Delegate.CarroComprasDelegate.prototype.getLogedUser("", function(data) {
                    var list = model.models;
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