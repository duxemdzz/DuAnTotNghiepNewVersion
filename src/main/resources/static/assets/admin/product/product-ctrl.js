app.controller("product-ctrl", function($scope, $http) {

	$scope.items = [];
	$scope.cates = [];
	$scope.brand = [];
	$scope.discount = [];
	$scope.tag = [];
	$scope.form = {};
	$scope.checkChange = [];
	$scope.sortType = 'name'; // set the default sort type
	$scope.sortReverse = false;  // set the default sort order
	$scope.searchName = '';     // set the default search/filter term
	
	$scope.initialize = function() {
		//loadproduct
		$http.get("/TechnoShop/rest/products").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createDate = new Date(item.createDate)
			})
		});
		//load category
		$http.get("/TechnoShop/rest/categories").then(resp => {
			$scope.cates = resp.data;
		})
		//load brand
		$http.get("/TechnoShop/rest/brands").then(resp => {
			$scope.brand = resp.data;
		})
		//load tag
		$http.get("/TechnoShop/rest/tagProduct").then(resp => {
			$scope.tag = resp.data;
		})
		//load discount
		$http.get("/TechnoShop/rest/discount").then(resp => {
			$scope.discount = resp.data;
		})

	}

	//khoi dau
	$scope.initialize();

	$scope.reset = function() {
		$scope.form = {
			createDate: new Date(),
			mainImage: 'cloud-upload.jpg',
			avaliable: true,
			isDisable: false,
		}
	}
	$scope.resetChange = function() {
		$scope.checkChange = {
		}
	}
	$scope.resetformConfig = function() {
			$scope.formConfig = {
			}
		}
	$scope.images = {};
	$scope.edit = function(item) {
		//load image
		$http.get(`/TechnoShop/rest/image/product/${item.id}`).then(resp => {
			$scope.images = resp.data;
		})
		$scope.form = angular.copy(item);
		$scope.checkChange = angular.copy(item);
		$scope.formConfig = angular.copy(item);
		$scope.editConfig(item);		
		$(".nav-pills a:eq(1)").tab('show');
		
	}
	
	$scope.create = function() {
		var item = angular.copy($scope.form);
		var mau =/^[1-9]+[0-9]{3,}$/
		if(item.name==null){
			Swal.fire({
				icon: 'error',
				title: 'Không được bỏ trống tên!',
				type: 'error',
				timer:'2000'
			})
		}else if(item.category==null){
			Swal.fire({
				icon: 'error',
				title: 'Không được bỏ trống danh mục!',
				type: 'error',
				timer:'2000'
			})
		}else if(item.price==null){
			Swal.fire({
				icon: 'error',
				title: 'Không được bỏ trống giá!',
				type: 'error',
				timer:'2000'
			})
		}else if(!mau.test(item.price)){
			Swal.fire({
				icon: 'error',
				title: 'Giá phải lớn hơn 1000!',
				type: 'error',
				timer:'2000'
			})
		}else if(item.avaliable==null){
			Swal.fire({
				icon: 'error',
				title: 'Không được bỏ trống trạng thái!',
				type: 'error',
				timer:'2000'
			})
		}else{
		$http.post('/TechnoShop/rest/products', item).then(resp => {
			resp.data.createDate = new Date(resp.data.createDate);			
			$scope.items.push(resp.data);
			$scope.reset();
			Swal.fire('Thêm thành công', '', 'success')
			$(".nav-pills a:eq(0)").tab('show');
		}).catch(error => {
			Swal.fire('Thêm thất bại', '', 'error')
			console.log("Error", error);
		})
	}
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		var check = angular.copy($scope.checkChange);
		console.log(item);
		console.log(check);
			if(check.category.id != item.category.id){
				console.log("đã thay đổi danh mục");
				var change = angular.copy($scope.formConfig);
							if(change.id != null){
									const swalWithBootstrapButtons = Swal.mixin({
										customClass: {
											confirmButton: 'btn btn-success',
											cancelButton: 'btn btn-danger'
										},
										buttonsStyling: false
									})
				
									swalWithBootstrapButtons.fire({
										title: 'Bạn có chắc chắn thay đổi danh mục?',
										text: "Trước khi thay đổi danh mục sẽ xóa cấu hình sản phẩm!",
										icon: 'question',
										showCancelButton: true,
										confirmButtonText: 'Yes',
										cancelButtonText: 'No',
										reverseButtons: true
								}).then((result) => {
									if(check.category.id == 'mobile'){
										if (result.isConfirmed) {
											$http.delete(`/TechnoShop/rest/configPhone/${change.id}`, change).then(resp => {
											Swal.fire('Thay đổi danh mục thành công', '', 'success')
												$http.put(`/TechnoShop/rest/products/${item.id}`, item).then(resp => {
													var index = $scope.items.findIndex(p => p.id == item.id);
													$scope.items[index] = item;
													Swal.fire('Cập nhật thành công', '', 'success')
													$scope.resetChange();
													$scope.reset();
													$scope.resetformConfig();
													$(".nav-pills a:eq(0)").tab('show');
												}).catch(error => {
													Swal.fire('Cập nhật thất bại', '', 'error')
													console.log("Error", error);
												})
											}).catch(error => {
												Swal.fire('Thay đổi danh mục thất bại', '', 'error')
												console.log("Error", error);
											})
										}
										
									}else if(check.category.id == 'laptop'){
											if (result.isConfirmed) {
												$http.delete(`/TechnoShop/rest/configLaptop/${change.id}`, change).then(resp => {
												Swal.fire('Thay đổi danh mục thành công', '', 'success')
													$http.put(`/TechnoShop/rest/products/${item.id}`, item).then(resp => {
													var index = $scope.items.findIndex(p => p.id == item.id);
														$scope.items[index] = item;
														Swal.fire('Cập nhật thành công', '', 'success')
														
														$scope.reset();
														$scope.resetChange();
														$scope.resetformConfig();
														$(".nav-pills a:eq(0)").tab('show');
													}).catch(error => {
													Swal.fire('Cập nhật thất bại', '', 'error')
													console.log("Error", error);
													})
												
												}).catch(error => {
													Swal.fire('Thay đổi danh mục thất bại', '', 'error')
													console.log("Error", error);
												})
											}
											
									}
									
								})
					}else{
						$http.put(`/TechnoShop/rest/products/${item.id}`, item).then(resp => {
						var index = $scope.items.findIndex(p => p.id == item.id);
						$scope.items[index] = item;
						Swal.fire('Cập nhật thành công', '', 'success')
						$scope.resetChange();
						$scope.reset();
						$scope.resetformConfig();
						$(".nav-pills a:eq(0)").tab('show');
						}).catch(error => {
							Swal.fire('Cập nhật thất bại', '', 'error')
							console.log("Error", error);
						})
					}
				}else{
						$http.put(`/TechnoShop/rest/products/${item.id}`, item).then(resp => {
						var index = $scope.items.findIndex(p => p.id == item.id);
						$scope.items[index] = item;
						Swal.fire('Cập nhật thành công', '', 'success')
						$scope.resetChange();
						$scope.reset();
						$scope.resetformConfig();
						$(".nav-pills a:eq(0)").tab('show');
						}).catch(error => {
							Swal.fire('Cập nhật thất bại', '', 'error')
							console.log("Error", error);
						})
					}
			
		
		
	}

	$scope.delete = function(item) {
		
		const swalWithBootstrapButtons = Swal.mixin({
			customClass: {
				confirmButton: 'btn btn-success',
				cancelButton: 'btn btn-danger'
			},
			buttonsStyling: false
		})

		swalWithBootstrapButtons.fire({
			title: 'Bạn có chắc muốn xóa nó không?',
			text: "Xóa sản phẩm",
			icon: 'question',
			showCancelButton: true,
			confirmButtonText: 'Yes',
			cancelButtonText: 'No',
			reverseButtons: true
		}).then((result) => {			
			if (result.isConfirmed) {
				item.isDisable = true;
				item.avaliable = false;
				$http.put(`/TechnoShop/rest/products/${item.id}`, item).then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items[index] = item;
				Swal.fire('Xóa thành công', '', 'success')
				$scope.reset();
				$scope.initialize();
				$(".nav-pills a:eq(0)").tab('show');
				}).catch(error => {
					Swal.fire('Xóa thất bại', '', 'error')
					console.log("Error", error);
				})
			}
		})

	}

	$scope.imageChanged = function(files) {
		var data = new FormData;
		
		data.append('file', files[0]);
		$http.post('/TechnoShop/rest/upload/product/', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.mainImage = resp.data.name;
		}).catch(error => {
			alert("loi upload");
			console.log("Error", error)
		})

	}

	$scope.reset();

	$scope.pager = {
		page: 0,
		size: 8,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);

		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}

	}

// cofig init
	$scope.screens = [];
	$scope.connects = [];
	$scope.utilitys = [];
	$scope.batterys = [];
	$scope.generals = [];
	$scope.formConfig = {};
	$scope.itemConfig=[];
	$scope.graphicsSounds = [];
	$scope.initializeConfig =function() {
		var item = angular.copy($scope.checkChange);
		//loadscreen
		$http.get(`/TechnoShop/rest/screen/${item.category.id}`).then(resp => {
			$scope.screens = resp.data;
		});
		//load connect
		$http.get(`/TechnoShop/rest/connect/${item.category.id}`).then(resp => {
			$scope.connects = resp.data;
		})
		//load brand
		$http.get(`/TechnoShop/rest/utility/${item.category.id}`).then(resp => {
			$scope.utilitys = resp.data;
		})
		//load brand
		$http.get(`/TechnoShop/rest/batteryCharges/${item.category.id}`).then(resp => {
			$scope.batterys = resp.data;
		})
		//load brand
		$http.get(`/TechnoShop/rest/generalInformation/${item.category.id}`).then(resp => {
			$scope.generals = resp.data;
		})

	}
	$scope.editConfig = function(item) {		
			$scope.form = angular.copy(item);
			$scope.checkChange = angular.copy(item);
			itemInit = angular.copy(item);
			if(itemInit.category.id == 'mobile')
			{
				console.log(item.id);
				$http.get(`/TechnoShop/rest/configPhone/${itemInit.id}`).then(resp => {
					$scope.formConfig = resp.data;
					if($scope.formConfig.id == null){
						$scope.formConfig = {
							category: {},
							product: {},
							screen: {id: null},
							utility: {id: null},
							batteryCharge: {id: null},
							connect: {id: null},
							generalInformation : {id: null}
						};
						$scope.initializeConfig();
						$(".nav-pills a:eq(2)").tab('show');
					}
				});
				console.log($scope.formConfig);
				$scope.initializeConfig();
				$(".nav-pills a:eq(2)").tab('show');
			}else if(itemInit.category.id == 'laptop'){
				console.log(itemInit.id);
				$http.get(`/TechnoShop/rest/configLaptop/${itemInit.id}`).then(resp => {
					$scope.formConfig = resp.data;
					$http.get(`/TechnoShop/rest/graphicsSound`).then(resp => {
							$scope.graphicsSounds = resp.data;
						});
					if($scope.formConfig.id == null){
						$scope.formConfig = {
							category: {},
							product: {},
							screen: {id: null},
							utility: {id: null},
							generalInformation : {id: null}
							
						};
						$scope.initializeConfig();
						$(".nav-pills a:eq(2)").tab('show');
					}
				});
				console.log($scope.formConfig);
				$scope.initializeConfig();
				$(".nav-pills a:eq(2)").tab('show');
			};
		}
	$scope.createConfigPhone = function() {
		var item = angular.copy($scope.formConfig);
		var itemp = angular.copy($scope.form);
		item.category.id = itemp.category.id
		item.product.id = itemp.id
		console.log(item);
		console.log(itemp);
//		var mau =/^[1-9]+[0-9]{3,}$/
		if(item.operatingSystem==null){
			Swal.fire({
				icon: 'warning',
				title: 'Không bỏ trống hệ điều hành!',
				type: 'warning',
				timer:'2000'
			})
		}else if(item.ram==null){
			Swal.fire({
				icon: 'warning',
				title: 'Không được bỏ trống ram!',
				type: 'warning',
				timer:'2000'
			})
		}else if(item.rom==null){
			Swal.fire({
				icon: 'warning',
				title: 'Không được bỏ trống rom!',
				type: 'warning',
				timer:'2000'
			})
		}else if(item.cpu==null){
			Swal.fire({
				icon: 'error',
				title: 'Không được bỏ trống cpu!',
				type: 'error',
				timer:'2000'
			})
		}else if(item.cameraBefore==null){
			Swal.fire({
				icon: 'error',
				title: 'Không được bỏ trống camare trước!',
				type: 'error',
				timer:'2000'
			})
		}else if(item.cameraAfter==null){
			Swal.fire({
				icon: 'error',
				title: 'Không được bỏ trống camare sau!',
				type: 'error',
				timer:'2000'
			})
		}else if(item.screen.id == null){
			Swal.fire({
				icon: 'error',
				title: 'Hãy chọn màn hình phù hợp!',
				type: 'error',
				timer:'2000'
			})
		}else if(item.batteryCharge.id==null){
			Swal.fire({
				icon: 'error',
				title: 'Hãy chọn pin phù hợp!',
				type: 'error',
				timer:'2000'
			})
		}else if(item.connect.id==null){
			Swal.fire({
				icon: 'error',
				title: 'Hãy chọn kết nối phù hợp!',
				type: 'error',
				timer:'2000'
			})
		}else if(item.utility.id==null){
			Swal.fire({
				icon: 'error',
				title: 'Hãy chọn tiện ích phù hợp!',
				type: 'error',
				timer:'2000'
			})
		}else if(item.generalInformation.id==null){
			Swal.fire({
				icon: 'error',
				title: 'Hãy chọn cấu tạo phù hợp!',
				type: 'error',
				timer:'2000'
			})
		}else{
		$http.post('/TechnoShop/rest/configPhone', item).then(resp => {					
			Swal.fire('Thêm thành công', '', 'success')
			$scope.resetformConfig();
			$scope.reset();
			$scope.resetChange();
			$(".nav-pills a:eq(0)").tab('show');
		}).catch(error => {
			Swal.fire('Thêm thất bại', '', 'error')
			console.log("Error", error);
		})
	}
	}
	
	$scope.updateConfigPhone = function() {
		var item = angular.copy($scope.formConfig);
		console.log(item);
		$http.put(`/TechnoShop/rest/configPhone/${item.id}`, item).then(resp => {
			Swal.fire('Cập nhật thành công', '', 'success')
			$(".nav-pills a:eq(0)").tab('show');
		}).catch(error => {
			Swal.fire('Cập nhật thất bại', '', 'error')
			console.log("Error", error);
		})
	}
	
	$scope.deleteConfigPhone = function() {
		var item = angular.copy($scope.formConfig);
		const swalWithBootstrapButtons = Swal.mixin({
			customClass: {
				confirmButton: 'btn btn-success',
				cancelButton: 'btn btn-danger'
			},
			buttonsStyling: false
		})

		swalWithBootstrapButtons.fire({
			title: 'Bạn có chắc muốn xóa nó không?',
			text: "Xóa sản phẩm",
			icon: 'question',
			showCancelButton: true,
			confirmButtonText: 'Yes',
			cancelButtonText: 'No',
			reverseButtons: true
		}).then((result) => {
			if (result.isConfirmed) {
				$http.delete(`/TechnoShop/rest/configPhone/${item.id}`, item).then(resp => {
				Swal.fire('Xóa thành công', '', 'success')
				$(".nav-pills a:eq(0)").tab('show');
				}).catch(error => {
					Swal.fire('Xóa thất bại', '', 'error')
					console.log("Error", error);
				})
			}
		})

	}
	
	// laptop
	$scope.createConfigLaptop = function() {
		var item = angular.copy($scope.formConfig);
		var itemp = angular.copy($scope.form);
		item.category.id = itemp.category.id
		item.product.id = itemp.id
		console.log(item);
		console.log(itemp);
//		var mau =/^[1-9]+[0-9]{3,}$/
		if(item.operatingSystem==null){
			Swal.fire({
				icon: 'warning',
				title: 'Không Được Hệ Điều Hành!',
				type: 'warning',
				timer:'2000'
			})
		}else if(item.hardDriver==null){
			Swal.fire({
				icon: 'warning',
				title: 'Không được bỏ ram!',
				type: 'warning',
				timer:'2000'
			})
		}else if(item.maxHardDriver==null){
			Swal.fire({
				icon: 'warning',
				title: 'Không được bỏ trống rom!',
				type: 'warning',
				timer:'2000'
		})		
		}else{
		$http.post('/TechnoShop/rest/configLaptop', item).then(resp => {					
			Swal.fire('Thêm thành công', '', 'success')
			$(".nav-pills a:eq(0)").tab('show');
		}).catch(error => {
			Swal.fire('Thêm thất bại', '', 'error')
			console.log("Error", error);
		})
	}
	}
	
	$scope.updateConfigLaptop = function() {
		var item = angular.copy($scope.formConfig);
		console.log(item);
		$http.put(`/TechnoShop/rest/configLaptop/${item.id}`, item).then(resp => {
			Swal.fire('Cập nhật thành công', '', 'success')
			$(".nav-pills a:eq(0)").tab('show');
		}).catch(error => {
			Swal.fire('Cập nhật thất bại', '', 'error')
			console.log("Error", error);
		})
	}
	
	$scope.deleteConfigLaptop = function() {
		var item = angular.copy($scope.formConfig);
		const swalWithBootstrapButtons = Swal.mixin({
			customClass: {
				confirmButton: 'btn btn-success',
				cancelButton: 'btn btn-danger'
			},
			buttonsStyling: false
		})

		swalWithBootstrapButtons.fire({
			title: 'Bạn có chắc muốn xóa nó không?',
			text: "Xóa sản phẩm",
			icon: 'question',
			showCancelButton: true,
			confirmButtonText: 'Yes',
			cancelButtonText: 'No',
			reverseButtons: true
		}).then((result) => {
			if (result.isConfirmed) {
				$http.delete(`/TechnoShop/rest/configLaptop/${item.id}`, item).then(resp => {
				Swal.fire('Xóa thành công', '', 'success')
				$(".nav-pills a:eq(0)").tab('show');
				}).catch(error => {
					Swal.fire('Xóa thất bại', '', 'error')
					console.log("Error", error);
				})
			}
		})

	}
})