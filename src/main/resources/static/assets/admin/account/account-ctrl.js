app.controller("account-ctrl", function($scope, $http) {
	$scope.authorities = [];
	$scope.items = [];
	$scope.form = {photo: 'cloud-upload.jpg'};
	$scope.roles = [];
	$scope.sortType = 'username';
	$scope.sortReverse = false;
	$scope.searchName = '';
//	let z = document.getElementById('quyendaglg');
//	let t = z.textContent;
//	$scope.role = t;
	$scope.initialize = function() {
		$http.get("/TechnoShop/rest/users").then(resp => {
			$scope.items = resp.data;
		})

		$http.get("/TechnoShop/rest/role").then(resp => {
			$scope.roles = resp.data;
		})
	}

	$scope.initialize();


	$scope.reset = function() {
		$scope.form = {
			photo: 'cloud-upload.jpg'
		}
	};


	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
		$(".nav-pills a:eq(0)").tab('show')
	}

	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post('/TechnoShop/rest/users', item).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			if (index == -1) {				
				resp.data.createDate = new Date(resp.data.createDate)
				$scope.items.push(resp.data);
				$scope.reset();
				Swal.fire('Thêm thành công', '', 'success')
			} else {
				Swal.fire('Tài khoản này đã tồn tại!', '', 'warning')
			}

		}).catch(error => {
			Swal.fire('Thêm thất bại', '', 'error')
			console.log("Error", error);
		})
	}

	$scope.update = function() {
		var item = angular.copy($scope.form);
		if(item.username != null){
				
			$http.put(`/TechnoShop/rest/users/${item.username}`, item).then(resp => {
				var index = $scope.items.findIndex(p => p.id == item.id);
				$scope.items[index] = item;
				Swal.fire('Cập nhật thành công', '', 'success')
				$scope.reset();
				$(".nav-pills a:eq(1)").tab('show')
			}).catch(error => {
				Swal.fire('Cập nhật thất bại', '', 'error')
				console.log("Error", error);
			})
		}else{
				Swal.fire('Cập nhật thất bại không tìm thấy người dùng cần cập nhật.', '', 'warning')
				console.log("Warning", error);
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
			text: "Xóa tài khoản",
			icon: 'question',
			showCancelButton: true,
			confirmButtonText: 'Yes',
			cancelButtonText: 'No',
			reverseButtons: true
		}).then((result) => {
			if (result.isConfirmed) {
				if(item.username != null){
					$http.delete(`/TechnoShop/rest/users/${item.username}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id == item.id);
					$scope.items.splice(index, 1);
					Swal.fire('Xóa thành công', '', 'success')
					$scope.reset();
					$(".nav-pills a:eq(1)").tab('show')
				
				}).catch(error => {
					Swal.fire('Xóa thất bại', '', 'error')
					console.log("Error", error);
				})
				
				}else{
					Swal.fire('Xóa thất bại không tìm thấy người dùng cần xóa !!!', '', 'warning')
					console.log("Warning", error);
				}
			}
		})

	}

	$scope.imageChanged = function(files) {
		var data = new FormData;
		data.append('file', files[0]);
		$http.post('/TechnoShop/rest/upload/avatar', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.photo = resp.data.name;
		}).catch(error => {
			alert("loi upload");
			console.log("Error", error)
		})

	}



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