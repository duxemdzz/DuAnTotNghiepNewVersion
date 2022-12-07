app.controller("battery-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.form = {};
	$scope.cates = [];
	$scope.sortType = 'id';
	$scope.sortReverse = false;
	$scope.searchName = '';

	$scope.initialize = function() {
		//load categories
		$http.get(`/TechnoShop/rest/categories`).then(resp => {
			$scope.cates = resp.data;
		})
		$http.get(`/TechnoShop/rest/batteryCharges`).then(resp => {
			$scope.items = resp.data;
		})
	}

    //khoi dau
	$scope.initialize();

    //nut lam moi
	$scope.reset = function() {
		$scope.form = {
		}
	};

    //nut edit
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-pills a:eq(1)").tab('show')
	}
	
	//nut them
	$scope.create = function() {
		var item = angular.copy($scope.form);
		if(item.advanceSecurity==null){
			Swal.fire({
				icon: 'error',
				title: 'Vui lòng nhập thông tin bảo mật !',
				type: 'error',
				timer:'2000'
			})
		}else if(item.category.id==null){
			Swal.fire({
				icon: 'error',
				title: 'Vui lòng chọn danh mục!',
				type: 'error',
				timer:'2000'
			})
		}else if(item.sfecialFeature==null){
			Swal.fire({
				icon: 'error',
				title: 'Vui lòng nhập tiện ích!',
				type: 'error',
				timer:'2000'
			})
		
		}else{
			$http.post('/TechnoShop/rest/batteryCharges', item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			if (index == -1) {
				$scope.items.push(resp.data);
				Swal.fire('Thêm thành công', '', 'success')
				$scope.reset();
				$(".nav-pills a:eq(0)").tab('show')
			} else {
				Swal.fire('Tiện ích được thêm này đã tồn tại!', '', 'error')
			}
		}).catch(error => {
			Swal.fire('Thêm thất bại', '', 'error')
			console.log("Error", error);
		})
	 }
	}
	
	//nut cap nhat
	$scope.update = function() {
		var item = angular.copy($scope.form);
		if(item.advanceSecurity==null){
			Swal.fire({
				icon: 'error',
				title: 'Vui lòng nhập thông tin bảo mật !',
				type: 'error',
				timer:'2000'
			})
		}else if(item.category.id==null){
			Swal.fire({
				icon: 'error',
				title: 'Vui lòng chọn danh mục!',
				type: 'error',
				timer:'2000'
			})
		}else if(item.sfecialFeature==null){
			Swal.fire({
				icon: 'error',
				title: 'Vui lòng nhập tiện ích!',
				type: 'error',
				timer:'2000'
			})
		
		}else{
			if(item.id != null){
			$http.put(`/TechnoShop/rest/batteryCharges/${item.id}`, item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			Swal.fire('Cập nhật thành công', '', 'success')
			$scope.reset();
			$(".nav-pills a:eq(0)").tab('show')
		}).catch(error => {
			Swal.fire('Cập nhật thất bại', '', 'error')
			console.log("Error", error);
		})
		}else{
				Swal.fire('Cập nhật thất bại không tìm thấy id danh mục cần cập nhật!', '', 'warning')
				console.log("Warning", error);
		}
	 }
	}
		
	//nut xoa
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
			text: "Xóa tiện ích vừa chọn.",
			icon: 'question',
			showCancelButton: true,
			confirmButtonText: 'Yes',
			cancelButtonText: 'No',
			reverseButtons: true
		}).then((result) => {
		if (result.isConfirmed) {
				if(item.id != null){
					$http.delete(`/TechnoShop/rest/batteryCharges/${item.id}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items.splice(index, 1);
					Swal.fire('Xóa thành công.', '', 'success')
					$scope.reset();
					$(".nav-pills a:eq(1)").tab('show')
				}).catch(error => {
					Swal.fire('Xóa thất bại.', '', 'error')
					console.log("Error", error);
				})
				}else{
					Swal.fire('Xóa thất bại không tìm id danh mục cần xóa!', '', 'warning')
					console.log("Warning", error);
				}
			}
		})
	}
	
    //phan trang
	$scope.pager = {
		page: 0,
		size: 10,
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
})