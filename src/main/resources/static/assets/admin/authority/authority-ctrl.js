
app.config(function($httpProvider) {
	$httpProvider.defaults.headers.common['Authorization'] = "Basic dXNlcjE6MTIz";
});
app.controller("authority-ctrl", function($scope, $http, $location) {

	$scope.roles = [];
	$scope.admins = [];
	$scope.authorities = [];
	$scope.initialize = function() {
		$http.get("/TechnoShop/rest/roles").then(resp => {
			$scope.roles = resp.data;
		})

		$http.get("/TechnoShop/rest/accounts?admin=true").then(resp => {
			$scope.admins = resp.data;
		})

		$http.get("/TechnoShop/rest/authorities?admin=true").then(resp => {
			$scope.authorities = resp.data;
		}).catch(error => {
			console.log(error)
		})
	}

	$scope.authority_of = function(acc, role) {
		if ($scope.authorities) {
			return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
		}
	}

	$scope.authority_changed = function(acc, role) {
		var authority = $scope.authority_of(acc, role);
		let user = ' ' + acc.username;
		let x = document.getElementById('tkdaglg');
		let y = x.textContent;
		if (y != user) {
			if (authority) {
				$scope.revoke_authority(authority);
			} else {
				authority = { account: acc, role: role };
				$scope.grant_authority(authority);
			}
		} else {
			Swal.fire({
				icon: 'error',
				title: 'Bạn không được hủy bỏ hay tự cấp quyền chính mình!',
				type: 'error',
				timer: 3000
			})
			$scope.initialize();
		}
	}

	$scope.grant_authority = function(authority) {
		var json = JSON.stringify(angular.copy([authority]));
		localStorage.setItem("authority", json);
		$http.post(`/TechnoShop/rest/authorities`, authority).then(resp => {
			$scope.authorities.push(resp.data);
			Swal.fire({
				icon: 'success',
				title: 'Cấp quyền thành công!',
				type: 'success',
				timer: 3000
			})
		}).catch(error => {
			Swal.fire({
				icon: 'error',
				title: 'Cấp quyền thất bại!',
				type: 'error',
				timer: 3000
			})
			console.log("Error", error);
		})
	}

	$scope.revoke_authority = function(authority) {
		$http.delete(`/TechnoShop/rest/authorities/${authority.id}`).then(resp => {
			var index = $scope.authorities.findIndex(a => a.id == authority.id);
			$scope.authorities.splice(index, 1);
			Swal.fire({
				icon: 'success',
				title: 'Thu hồi thành công!',
				type: 'success',
				timer: 3000
			})
			$scope.initialize();
		}).catch(error => {
			Swal.fire({
				icon: 'error',
				title: 'Thu hồi thất bại',
				type: 'error',
				timer: 3000
			})
			console.log("Error", error);
		})
	}
	$scope.initialize();
})