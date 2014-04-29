define(['controller/selectionController', 'model/cacheModel', 'model/carroComprasMasterModel', 'component/_CRUDComponent', 'controller/tabController', 'component/carroComprasComponent',
    'component/itemComponent', 'delegate/loginDelegate'

], function(SelectionController, CacheModel, CarroComprasMasterModel, CRUDComponent, TabController, CarroComprasComponent,
        ItemComponent
        ) {
    App.Component.CarroComprasMasterComponent = App.Component.BasicComponent.extend({
        initialize: function() {
            var self = this;
            this.configuration = App.Utils.loadComponentConfiguration('carroComprasMaster');
            var uComponent = new CarroComprasComponent();
            uComponent.initialize();
            uComponent.render('main');
            
            /* Aqui se hace la llamada al delegate para obtener el "user stack" (html con el nombre y el link de cerrar 
             * sesión*/
            App.Delegate.LoginDelegate.getLogedUserStack("", function(data) {

                /*Se crea un div flotante con la libreria js referenciada en el index*/
                floatingMenu.add('floatdiv',
                        {
                            targetRight: 10,
                            targetTop: 10,
                            snap: true
                        });
                /*con jquery, se agrega el contenido del "user stack" al div flotante creado anteriormente*/
                $("#floatdiv").html("<table width=\"100%\" height=\"100%\" ><tr><td align=\"center\"><span class=\"glyphicon glyphicon-user\"></span></td><td>" + data + "</td></tr></table>");

            }, function(data) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'get logged user', view: self, id: '', data: data, error: {textResponse: 'Error in getting logged user'}});
            });



            Backbone.on(uComponent.componentId + '-post-carroCompras-create', function(params) {
                self.renderChilds(params);
                self.autoSelectLoggedClient(params);
            });
            Backbone.on(uComponent.componentId + '-post-carroCompras-edit', function(params) {
                self.renderChilds(params);
                
            });
            Backbone.on(uComponent.componentId + '-pre-carroCompras-list', function() {
                self.hideChilds();
            });
            Backbone.on('carroCompras-master-model-error', function(error) {
                Backbone.trigger(uComponent.componentId + '-' + 'error', {event: 'carroCompras-master-save', view: self, error: error});
            });
            Backbone.on(uComponent.componentId + '-instead-carroCompras-save', function(params) {
                self.model.set('carroComprasEntity', params.model);
                if (params.model) {
                    self.model.set('id', params.model.id);
                } else {
                    self.model.unset('id');
                }
                var itemModels = self.itemComponent.componentController.itemModelList;
                self.model.set('listItem', []);
                self.model.set('createItem', []);
                self.model.set('updateItem', []);
                self.model.set('deleteItem', []);
                for (var i = 0; i < itemModels.models.length; i++) {
                    var m = itemModels.models[i];
                    var modelCopy = m.clone();
                    if (m.isCreated()) {
                        //set the id to null
                        modelCopy.unset('id');
                        self.model.get('createItem').push(modelCopy.toJSON());
                    } else if (m.isUpdated()) {
                        self.model.get('updateItem').push(modelCopy.toJSON());
                    }
                }
                for (var i = 0; i < itemModels.deletedModels.length; i++) {
                    var m = itemModels.deletedModels[i];
                    self.model.get('deleteItem').push(m.toJSON());
                }
                self.model.save({}, {
                    success: function() {
                        uComponent.componentController.list();
                    },
                    error: function(error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'carroCompras-master-save', view: self, error: error});
                    }
                });
            });
        },
        //Esta función cambia el combo box 'clienteID' coon el usuario logeado automaticamente.
        autoSelectLoggedClient: function(params) {
            /*Función timeout para que alcancen a cargarse los controles bootstrap (animaciones)*/
            setTimeout(function() {
                /*se invoca la el servicio REST que obtiene el nombre del usuario logeado*/
                        App.Delegate.LoginDelegate.getLogedUser("", function(data) {
                            var useropt;
                            //Se busca el dropdown list de clientes
                            $('#clienteId > option').each(function() {
                                //Si encuentra un item con el nombre del usuario lo guarda en la variable
                                if(data == this.text){
                                    useropt = $(this);
                                }
                            });
                            //Vacia la lista
                            $('#clienteId').empty();
                            //Agrega unicamente el cliente encontrado, siendo este la única selección posible
                            $('#clienteId').append(useropt);
                            

                        }, function(data) {
                        });
                    }, 500);
        },
        renderChilds: function(params) {
            var self = this;
            this.tabModel = new App.Model.TabModel(
                    {
                        tabs: [
                            {label: "Item", name: "item", enable: true},
                        ]
                    }
            );

            this.tabs = new TabController({model: this.tabModel});

            this.tabs.render('tabs');
            App.Model.CarroComprasMasterModel.prototype.urlRoot = this.configuration.context;
            var options = {
                success: function() {
                    self.itemComponent = new ItemComponent();
                    self.itemModels = App.Utils.convertToModel(App.Utils.createCacheModel(App.Model.ItemModel), self.model.get('listItem'));
                    self.itemComponent.initialize({
                        modelClass: App.Utils.createCacheModel(App.Model.ItemModel),
                        listModelClass: App.Utils.createCacheList(App.Model.ItemModel, App.Model.ItemList, self.itemModels)
                    });
                    self.itemComponent.render(self.tabs.getTabHtmlId('item'));
                    Backbone.on(self.itemComponent.componentId + '-post-item-create', function(params) {
                        params.view.currentItemModel.setCacheList(params.view.itemModelList);
                    });
                    self.itemToolbarModel = self.itemComponent.toolbarModel.set(App.Utils.Constans.referenceToolbarConfiguration);
                    self.itemComponent.setToolbarModel(self.itemToolbarModel);



                    $('#tabs').show();
                    
                },
                error: function() {
                    Backbone.trigger(self.componentId + '-' + 'error', {event: 'carroCompras-edit', view: self, id: id, data: data, error: error});
                }
            };
            if (params.id) {
                self.model = new App.Model.CarroComprasMasterModel({id: params.id});
                
                self.model.fetch(options);
            } else {
                self.model = new App.Model.CarroComprasMasterModel();
                options.success();
            }


        },
        hideChilds: function() {
            $('#tabs').hide();
        }
    });

    return App.Component.CarroComprasMasterComponent;
});