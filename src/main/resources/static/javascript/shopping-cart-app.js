const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function($scope, $http) {
	$scope.checkLogin = false;
	$scope.photoCheckLength = false;
	$scope.form = {};
	$scope.userAddress = [];
	$scope.formUserAddress = {};
	$scope.formUserAddressEdit = {};
	$scope.formRegister = {};
	$scope.userAddressDefault = {};
	$scope.tinhs = [];
	$scope.huyen = [];
	$scope.dataTinh = function() {
		var token = "token: 3132415b-761b-11ed-b09a-9a2a48e971b0";
		const config = {
			headers: {
				token: "3132415b-761b-11ed-b09a-9a2a48e971b0",
				"Content-Type": "application/ json"
			}
		};
		$http.get(`https://online-gateway.ghn.vn/shiip/public-api/master-data/province`, config).then(resp => {
			$scope.tinhs = resp.data.data
			console.log($scope.tinhs)
			localStorage.setItem("province_id", "220");
		}).catch(error => {
			console.log(error)
		});
	}
	//$scope.dataTinh();

	$scope.dataHuyen = function() {
		var data = {
			"data-raw": { province_id: localStorage.getItem("province_id") }
		}
		var dataa = {
			"data-raw": {
				"province_id": 252
			}
		}
		var token = "Token: 3132415b-761b-11ed-b09a-9a2a48e971b0";
		const config = {
			headers: {
				token: "3132415b-761b-11ed-b09a-9a2a48e971b0",
				"Content-Type": "application/ json",
				"province_id": 252
			}
		};
		$http.get(`https://online-gateway.ghn.vn/shiip/public-api/master-data/district`, config).then(resp => {
			$scope.huyen = resp.data.data
			console.log($scope.huyen)
		}).catch(error => {
			console.log(error)
		});
	}
	//$scope.dataHuyen();
	$scope.SelectTinhThanh = function(item) {
		localStorage.setItem("province_id", item);
		console.log(item)

	}
	// Lấy thông tin đăng nhập
	$scope.account = function() {
		$http.get(`/TechnoShop/rest/users/remoteuser`).then(resp => {
			if (resp.data) {
				$scope.checkLogin = true;
			}
			if (resp.data.photo?.length > 20) {
				$scope.photoCheckLength = true;
			}
			$scope.form = resp.data;
			localStorage.setItem("user", JSON.stringify(resp.data))
		}).catch(error => {
			console.log(error)
		});
	}
	$scope.account();
	// tải tài khoản lên
	$scope.Select = function(name) {
		console.log(name)
		location.href = "/TechnoShop/product/page?field=" + name
	}
	// Đăng nhập đăng kí
	$scope.reset = function() {
		$scope.formRegister = {
			createDate: new Date(),
			photo: 'upload-image.jpg',
			isActive: false,
			isDisable: false,
		}
	}
	$scope.addReviewByProductId = function(productId) {
		var json = localStorage.getItem("user");
		if (!json) {
			console.log(json)
			Swal.fire({
				icon: 'error',
				title: 'Bạn chưa đăng nhập!',
				type: 'error',
				timer: '2000'
			})
		} else {

			var username = JSON.parse(json).username;
			var item = {
				rate: JSON.parse(localStorage.getItem("startNumber")) || 3,
				contents: document.getElementById("review-text").value,
				voteDate: new Date(),
				users: {
					username: username
				},
				product: {
					id: productId
				}
			}
			$http.post('/TechnoShop/rest/review', item).then(resp => {
				Swal.fire({
					icon: 'success',
					title: 'Thêm đánh giá thành công',
					type: 'success',
					timer: '2000'
				})
				localStorage.setItem("startNumber", 3);
				location.reload()
			})
		}

	}
	$scope.setDefault = function() {
		var json = localStorage.getItem("user");
		if (json) {
			var username = JSON.parse(json).username;
			$http.get('/TechnoShop/rest/userAddress/' + username).then(resp => {
				$scope.userAddressDefault = resp.data ? resp.data[0] : {};
				localStorage.setItem("addressDefault", JSON.stringify(resp.data[0]))
			})
		}
	}
	$scope.setDefault();

	$scope.data = function() {
		var json = localStorage.getItem("user");
		if (json) {
			var username = JSON.parse(json).username;
			$http.get('/TechnoShop/rest/userAddress/' + username,).then(resp => {
				$scope.userAddress = resp.data ? resp.data : [];
			})
		}

	}

	$scope.ChangeUserAddress = function(id) {
		$http.get('/TechnoShop/rest/userAddress/get/' + id,).then(resp => {
			$scope.userAddressDefault = resp.data ? resp.data : {};
			localStorage.setItem("addressDefault", JSON.stringify(resp.data))
		})
	}
	$scope.editAddress = function(id) {
		$http.get('/TechnoShop/rest/userAddress/get/' + id,).then(resp => {
			$scope.formUserAddressEdit = resp.data ? resp.data : {};
		})
	}
	$scope.data();
	$scope.reset();
	$scope.AddUserAddress = function() {
		var item = {
			fullname: $scope.formUserAddress.fullname,
			address: $scope.formUserAddress.address,
			phone: $scope.formUserAddress.phone,
			users: {
				username: JSON.parse(localStorage.getItem("user")).username,
			}
		};
		if ($scope.checkLogin) {
			$http.post('/TechnoShop/rest/userAddress', item).then(resp => {
				console.log(resp.data)
				$scope.formUserAddress = {}
				$scope.data();
				$scope.setDefault();
			})
		} else {
			Swal.fire({
				icon: 'error',
				title: 'Bạn chưa đăng nhập!',
				type: 'error',
				timer: '2000'
			})
			location.href = "/TechnoShop/account/login"
		}


	}
	$scope.UpdateAddress = function() {
		var edit = angular.copy($scope.formUserAddressEdit);
		var item = {
			id: edit.id,
			fullname: edit.fullname,
			address: edit.address,
			phone: edit.phone,
			users: {
				username: JSON.parse(localStorage.getItem("user")).username,
			}
		};
		if ($scope.checkLogin) {
			$http.put('/TechnoShop/rest/userAddress/' + edit.id, item).then(resp => {
				console.log(resp.data)
				$scope.formUserAddress = {}
				$scope.data();
			})
		} else {
			Swal.fire({
				icon: 'error',
				title: 'Bạn chưa đăng nhập!',
				type: 'error',
				timer: '2000'
			})
			location.href = "/TechnoShop/account/login"
		}
	}
	// Đăng kí tài khoản
	$scope.registerAccount = function() {
		var mau = /^[a-z0-9]+@+[a-z]+.[a-z]+.[a-z]{2,10}$/
		var mau1 = /^\w+$/
		var item = angular.copy($scope.formRegister);
		if (!item.username) {
			Swal.fire({
				icon: 'error',
				title: 'Không được bỏ trống tên đăng nhập',
				type: 'error',
				timer: '2000'
			})
		} else if (!mau1.test(item.username)) {
			Swal.fire({
				icon: 'error',
				title: 'Tên đăng nhập không chứa dấu tiếng việt hoặc kí tự đặc biệt!',
				type: 'error',
				timer: '2000'
			})
		}
		else if (!item.firstName) {
			Swal.fire({
				icon: 'error',
				title: 'Không được bỏ trống tên của bạn!',
				type: 'error',
				timer: '2000'
			})
		}
		else if (!mau.test(item.email)) {
			Swal.fire({
				icon: 'error',
				title: 'Không được bỏ trống tên của bạn!',
				type: 'error',
				timer: '2000'
			})
		} else if (!mau.test(item.email)) {
			Swal.fire({
				icon: 'error',
				title: 'Không đúng định dạng email!',
				type: 'error',
				timer: '2000'
			})
		}
		else if (!item.password) {
			Swal.fire({
				icon: 'error',
				title: 'Không được bỏ trống mật khẩu!',
				type: 'error',
				timer: '2000'
			})
		}
		else if (item.password.length < 6) {
			Swal.fire({
				icon: 'error',
				title: 'Mật khẩu phải có ít nhất 6 kí tự!',
				type: 'error',
				timer: '2000'
			})
		}
		else {
			var check = false;
			var item = angular.copy($scope.formRegister);
			$http.get('/TechnoShop/rest/users').then(resp => {
				resp.data.map(itemchild => {
					if (item.username === itemchild.username) {
						check = true;
					}
					return check;
				})
				if (check) {
					Swal.fire({
						icon: 'error',
						title: 'Tên đăng nhập đã tồn tại, vui lòng chọn tên đăng nhập khác!',
						type: 'error',
						timer: '3000'
					})
				} else {

					$http.post('/TechnoShop/rest/users', item).then(response => {
						try {
							$http.get(`/TechnoShop/mailer/${item.username}`)
							Swal.fire({
								icon: 'success',
								title: 'Đăng kí thành công, kiểm tra email để kích hoạt nó!',
								type: 'success',
								timer: '3000'
							})
							$scope.reset();
						} catch {
							Swal.fire({
								icon: 'error',
								title: 'Đăng kí thất bại!',
								type: 'error',
								timer: '3000'
							})
						}

					}).catch(error => {
						Swal.fire({
							icon: 'error',
							title: 'Đăng kí thất bại!',
							type: 'error',
							timer: '3000'
						})
						console.log("Error", error);
					})

				}
			})

		}

	}

	// Cập nhật tài khoản
	$scope.updateAccount = function() {
		var mau = /^[a-z0-9]+@+[a-z]+.[a-z]+.[a-z]{2,10}$/
		var item = angular.copy($scope.form);
		if (!item.firstName) {
			Swal.fire({
				icon: 'error',
				title: 'Không được bỏ trống tên',
				type: 'error',
				timer: '2000'
			})
		} else if (!item.email) {
			Swal.fire({
				icon: 'error',
				title: 'Không được bỏ trống email!',
				type: 'error',
				timer: '2000'
			})
		} else if (!mau.test(item.email)) {
			Swal.fire({
				icon: 'error',
				title: 'Không đúng định dạng email!',
				type: 'error',
				timer: '2000'
			})
		}
		else {
			$http.put(`/TechnoShop/rest/users/${item.username}`, item).then(resp => {
				Swal.fire({
					icon: 'success',
					title: 'Cập nhật thành công!',
					type: 'success',
					timer: '3000'
				})

			}).catch(error => {
				Swal.fire({
					icon: 'error',
					title: 'Cập nhật thất bại!',
					type: 'error',
					timer: '3000'
				})
				$scope.account();
				console.log("Error", error);
			})
		}

	}
	/* QUẢN LÝ GIỎ HÀNG */
	$scope.cart = {
		items: [],
		// Thêm cartItem với id product

		addCartItem(id) {
			if ($scope.checkLogin) {
				var item = this.items.find(item => item.product.id == id)
				// sp !=null
				if (item) {
					if (item.quantity < item.product.inventory) {
						qty = document.getElementById('soluong').value;
						item.quantity = item.quantity + Number(qty);
						var json = localStorage.getItem("user");
						var username = JSON.parse(json).username;
						$http.get(`/TechnoShop/rest/cart/${username}`).then(response => {
							$http.get(`/TechnoShop/rest/cartItem/${response.data.id}/${id}`).then(responseChild => {
								$http.put(`/TechnoShop/rest/cartItem/${responseChild.data.id}`, {
									"id": responseChild.data.id,
									"quantity": item.quantity,
									"cart": { "id": response.data.id },
									"product": { "id": id }
								}).then(resp => {
									console.log(resp.data)
									this.saveCart();
									Swal.fire({
										icon: 'success',
										title: 'Đã thêm vào giỏ hàng!',
										type: 'success',
										timer: 1500

									});

								}).catch(error => {
									console.log(error)
								});
							})
						})
					} else {
						Swal.fire({
							icon: 'error',
							title: 'Lớn hơn số lượng hiện có!',
							type: 'error',
							timer: 1000
						})
					}

				} else {
					// sp ==null
					var json = localStorage.getItem("user");
					var username = JSON.parse(json).username;
					qty = document.getElementById('soluong').value;
					$http.get(`/TechnoShop/rest/cart/${username}`).then(response => {
						$http.post(`/TechnoShop/rest/cartItem`, {
							"quantity": Number(qty),
							"cart": { "id": response.data.id },
							"product": { "id": id }
						}).then(resp => {
							console.log(resp.data);
							this.saveCart();
							$scope.cart.loadCartByUsername();
							Swal.fire({
								icon: 'success',
								title: 'Đã thêm vào giỏ hàng!',
								type: 'success',
								timer: 1500

							});
						}).catch(error => {
							console.log(error)
						});
					})

				}
			} else {
				let timerInterval
				Swal.fire({
					icon: 'error',
					title: 'Bạn phải đăng nhập trước khi thêm giỏ hàng',
					type: 'error',
					timer: 1000,
					willClose: () => {
						clearInterval(timerInterval)
					}
				}).then((result) => {
					if (result.dismiss === Swal.DismissReason.timer) {
						window.location.href = "/TechnoShop/account/login"
					}
				})
			}
		},// End Thêm cart với id product

		// Xóa CartItem với id cartItem
		removeCartItem(id) {
			var index = this.items.findIndex(item =>
				item.id = id
			);
			$http.delete(`/TechnoShop/rest/cartItem/${id}`).then(resp => {
				this.items.splice(index, 1);
				this.saveCart();
				console.log(resp.data)
			}).catch(err => {
				console.log(err)
			})

		},// End Xóa Cart với id cartItem


		// Xóa All CartItem với id cart không có thông báo
		clearCartItemByCartIdnotb() {
			var json = localStorage.getItem("user");
			var username = JSON.parse(json).username;
			$http.get(`/TechnoShop/rest/cart/${username}`).then(response => {
				$http.get(`/TechnoShop/rest/cartItem/${response.data.id}`).then(resp => {
					resp.data.map(cart => {
						var index = this.items.findIndex(item =>
							item.cart.id = cart.id
						);
						$http.delete(`/TechnoShop/rest/cartItem/${cart.id}`);
						this.items.splice(index, 1);
						this.saveCart();
					})
				})

			})
		},// EndXóa All CartItem với id cart không có thông báo


		// Xóa All CartItem với id cart có thông báo
		clearCartItemByCartId() {
			if (($scope.cart.items
				.map(item => item.quantity)
				.reduce((total, qty) => total += qty, 0)) > 0) {
				swal.fire({
					title: 'Bạn có chắc muốn xóa toàn bộ sản phẩm chứ?',
					text: "Xóa sạch giỏ hàng",
					icon: 'question',
					showCancelButton: true,
					confirmButtonColor: '#3085d6',
					cancelButtonColor: '#d33',
					confirmButtonText: 'Có',
					cancelButtonText: 'Không',
					reverseButtons: true
				}).then((result) => {
					if (result.isConfirmed) {
						var json = localStorage.getItem("user");
						var username = JSON.parse(json).username;
						$http.get(`/TechnoShop/rest/cart/${username}`).then(response => {
							$http.get(`/TechnoShop/rest/cartItem/${response.data.id}`).then(resp => {
								resp.data.map(cart => {
									var index = this.items.findIndex(item =>
										item.cart.id = cart.id
									);
									$http.delete(`/TechnoShop/rest/cartItem/${cart.id}`);
									this.items.splice(index, 1);
									this.saveCart();
								})
							})

						})

					}
				})
			} else {
				Swal.fire({
					icon: 'error',
					title: 'Chưa có mặt hàng nào trong giỏ!',
					type: 'error',
					timer: 2000
				})
			}

		},// Xóa All CartItem với id cart

		// Lấy số lượng của cart Id
		get count() {
			return this.items
				.map(item => item.quantity)
				.reduce((total, qty) => total += qty, 0);
		},// End Lấy số lượng cart Id

		// Lấy tổng tiền của cart Id
		get amount() {
			return this.items
				.map(item => ((item.product.price * (1 - (item.product.discount.discount / 100))) * item.quantity))
				.reduce((total, qty) => total += qty, 0);
		},// Lấy tổng tiền của cart Id

		get amountByPlushShipfee() {
			return this.items
				.map(item => (item.product.price - (item.product.price * (item.product.discount.discount / 100))) * item.quantity)
				.reduce((total, qty) =>
					total += qty, 0);
		},// Lấy tổng tiền của cart Id

		// save Cart
		saveCart() {
			var json = JSON.stringify(angular.copy(this.items));
		},// save Cart


		// load Cart lên
		loadCartByUsername() {
			var json = localStorage.getItem("user");
			if (json) {
				var username = JSON.parse(json).username;
				if (username) {
					$http.get(`/TechnoShop/rest/cart/${username}`).then(response => {
						$http.get(`/TechnoShop/rest/cartItem/${response.data.id}`).then(resp => {
							this.items = resp.data ? resp.data : [];
						})

					})
				}
			}

		},// save Cart

		// Tăng số lượng trong giỏ
		incrementQuantityByCartItem(id) {
			var item = this.items.find(item => item.product.id == id)
			// sp !=null
			if (item.quantity < item.product.inventory) {
				item.quantity++;
				var json = localStorage.getItem("user");
				var username = JSON.parse(json).username;
				$http.get(`/TechnoShop/rest/cart/${username}`).then(response => {
					$http.get(`/TechnoShop/rest/cartItem/${response.data.id}/${id}`).then(responseChild => {
						$http.put(`/TechnoShop/rest/cartItem/${responseChild.data.id}`, {
							"id": responseChild.data.id,
							"quantity": item.quantity,
							"cart": { "id": response.data.id },
							"product": { "id": id }
						}).then(resp => {
							console.log(resp.data)
							this.saveCart();
						}).catch(error => {
							console.log(error)
						});
					})
				})
			} else {
				Swal.fire({
					icon: 'error',
					title: 'Lớn hơn số lượng hiện có!',
					type: 'error',
					timer: 1000
				})
			}

		},// Tăng số lượng trong giỏ

		// Giảm số lượng trong giỏ
		decreaseQuantityByCartItem(id) {
			var item = this.items.find(item => item.product.id == id)
			if (item.quantity > 1) {
				// sp !=null
				item.quantity--;
				var json = localStorage.getItem("user");
				var username = JSON.parse(json).username;
				$http.get(`/TechnoShop/rest/cart/${username}`).then(response => {
					$http.get(`/TechnoShop/rest/cartItem/${response.data.id}/${id}`).then(responseChild => {
						$http.put(`/TechnoShop/rest/cartItem/${responseChild.data.id}`, {
							"id": responseChild.data.id,
							"quantity": item.quantity,
							"cart": { "id": response.data.id },
							"product": { "id": id }
						}).then(resp => {
							console.log(resp.data)
							//item.qty++;
							this.saveCart();
						}).catch(error => {
							console.log(error)
						});
					})
				})
			}
		},// Giảm số lượng trong giỏ

		// Tăng số lượng trong trang chi tiết
		incrementQuantityWhenAdd(id) {
			var inventory = document.getElementById('inventory').value;
			var inventoryNumber = Number(inventory);
			var laysl = document.getElementById('soluong').value;
			var layslNumber = Number(laysl) + 1;
			if (layslNumber >= inventoryNumber) {

				Swal.fire({
					icon: 'error',
					title: 'Lớn hơn số lượng hiện có!',
					type: 'error',
					timer: 1000
				})
				document.getElementById('soluong').value = inventoryNumber;
			} else {
				document.getElementById('soluong').value = layslNumber;
			}

		},// Tăng số lượng trong trang chi tiết

		// Giảm số lượng trong trang chi tiết
		decreaseQuantityWhenAdd(id) {
			var laysl = document.getElementById('soluong').value;
			if (Number(laysl) > 1) {
				var layslNumber = Number(laysl) - 1;
				document.getElementById('soluong').value = layslNumber;
			}
		},// Giảm số lượng trong trang chi tiết

		CheckQuantityByCartDetail() {
			if (($scope.cart.items
				.map(item => item.quantity)
				.reduce((total, qty) => total += qty, 0)) > 0) {
				window.location.href = "/TechnoShop/cart/detail"
			} else {
				Swal.fire({
					icon: 'error',
					title: 'Chưa có mặt hàng nào trong giỏ!',
					type: 'error',
					timer: 1000
				})

			}
		},
		CheckQuantityByCheckout() {
			if (($scope.cart.items
				.map(item => item.quantity)
				.reduce((total, qty) => total += qty, 0)) > 0) {
				window.location.href = "/TechnoShop/cart/checkout"
			} else {
				Swal.fire({
					icon: 'error',
					title: 'Chưa có mặt hàng nào trong giỏ!',
					type: 'error',
					timer: 1000
				})

			}
		}


	}
	// tải giỏ hàng lên
	$scope.cart.loadCartByUsername();

	// Đặt hàng
	$scope.order = {
		createDate: new Date(),
		message: "",
		isDelete: false,
		orderStatus: 0,
		shipperId: 1,
		codePayment: "0123456",
		payment: {
			id: "1"
		},
		get orderDetails() {
			return $scope.cart.items.map(item => {
				item.product.inventory = item.product.inventory - item.quantity;
				$http.put(`/TechnoShop/rest/products/${item.product.id}`, item.product).then(resp => {
					console.log(resp.data);
				}).catch(err => {
					console.log(err)
				})
				return {
					product: { id: item.product.id },
					price: item.product.price,
					quantity: item.quantity
				}
			});
		},

		purchase() {
			var addressShip = localStorage.getItem("addressDefault");
			var mau = /^[0]+[0-9]{9}$/
			if (($scope.cart.items
				.map(item => item.quantity)
				.reduce((total, qty) => total += qty, 0)) > 0) {
				if (addressShip == undefined) {
					Swal.fire({
						icon: 'error',
						title: 'Bạn chưa  địa chỉ!',
						type: 'error',
						timer: 3000
					})
				} else {
					var order = angular.copy(this);
					order.total = $scope.cart.amount
					order.shipFee = $scope.cart.amount > 2000000 ? 0 : 44000,
						order.users = {
							username: JSON.parse(localStorage.getItem("user")).username
						},
						order.address = JSON.parse(addressShip).address,
						order.phone = JSON.parse(addressShip).phone,
						order.fullname = JSON.parse(addressShip).fullname,
						// thực hiện đặt hàng
						console.log(order);
					$http.post("/TechnoShop/rest/order", order).then(resp => {
						Swal.fire({
							icon: 'success',
							title: 'Đặt hàng thành công!',
							type: 'success',
							timer: 3000
						})
						$scope.cart.clearCartItemByCartIdnotb();
						location.href = "/TechnoShop/order/detail/" + resp.data.id;

					}).catch(error => {
						Swal.fire({
							icon: 'error',
							title: 'Đặt hàng thất bại',
							type: 'error',
							timer: 3000
						})
						console.log(error)
					})
				}

			} else {
				Swal.fire({
					icon: 'error',
					title: 'Chưa có mặt hàng nào trong giỏ!',
					type: 'error',
					timer: 2000
				})
				window.location.href = "/TechnoShop"
			}
		}
	}

	// Upload Avatar
	$scope.UploadAvatar = function(files) {
		console.log($scope.photoCheckLength)
		if ($scope.photoCheckLength == false) {
			const file = files[0];
			try {
				if (file) {
					const getSizeImage = file.size;
					if (file.type === 'image/png' || file.type === 'image/jpeg') {
						if (getSizeImage > 1024 * 1024) {
							Swal.fire({
								icon: 'error',
								title: 'Chỉ cho phép tải tệp tin nhỏ hơn 1MB',
								showConfirmButton: false,
								timer: 1500,
							});
						} else {
							var data = new FormData;
							data.append('file', files[0]);
							$http.post('/TechnoShop/rest/upload/avatar', data, {
								transformRequest: angular.identity,
								headers: { 'Content-Type': undefined }
							}).then(resp => {
								$scope.form.photo = resp.data.name;
							}).catch(error => {
								Swal.fire({
									icon: 'error',
									title: 'Lỗi upload hình ảnh!',
									type: 'error',
									timer: 3000
								})
								console.log("Error", error)
							})
						}
					} else {
						Swal.fire({
							icon: 'error',
							title: 'Bạn phải chọn ảnh có đuôi .png hoặc .jpg',
							showConfirmButton: false,
							timer: 1500,
						});
					}
				}
			} catch (error) { }

		} else {
			Swal.fire({
				icon: 'error',
				title: 'Lỗi cập nhật hình ảnh!',
				type: 'error',
				timer: 3000
			})
		}


	}

	// Upload Product Image
	$scope.UploadProductImage = function(files) {
		var data = new FormData;
		data.append('file', files[0]);
		$http.post('/TechnoShop/rest/upload/product', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.photo = resp.data.name;
		}).catch(error => {
			Swal.fire({
				icon: 'error',
				title: 'Lỗi upload hình ảnh!',
				type: 'error',
				timer: 3000
			})
			console.log("Error", error)
		})

	}
	// Tài khoản

	// Lưu lại tài khoản người dùng
	$scope.wishlist = {
		wishlists: [],
		// Thêm wishlist với id product
		addWishListItem(id) {
			var item = this.wishlists.find(item => item.product.id == id)
			// sp !=null
			if (item) {
				Swal.fire({
					icon: 'error',
					title: 'Sản phẩm này đã có trong trang yêu thích',
					type: 'error',
					timer: 3000

				});

			} else {
				// sp ==null
				var json = localStorage.getItem("user");
				var username = JSON.parse(json).username;
				$http.post(`/TechnoShop/rest/wishList`, {
					"product": { "id": id },
					"users": { "username": username },
					"createDate": new Date()
				}).then(response => {
					Swal.fire({
						icon: 'success',
						title: 'Đã thêm vào danh sách yêu thích',
						type: 'success',
						timer: 1500

					});
				}).catch(err => {
					console.log(err)
				})

			}
		},// End Thêm cart với id product

		// Xóa CartItem với id cartItem
		removeWishlistId(id) {
			var index = this.wishlists.findIndex(item =>
				item.id = id
			);
			swal.fire({
				title: 'Bạn có chắc muốn xóa sản phẩm này chứ?',
				text: "Xóa sản phẩm yêu thích",
				icon: 'question',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: 'Có',
				cancelButtonText: 'Không',
				reverseButtons: true
			}).then((result) => {
				if (result.isConfirmed) {
					$http.delete(`/TechnoShop/rest/wishList/${id}`).then(resp => {
						this.wishlists.splice(index, 1);
						this.saveWishlist();
						this.loadWishlistByUsername();
						console.log(resp.data)
					}).catch(err => {
						console.log(err)
					})

				}
			})

		},// End Xóa Cart với id cartItem

		// save Wishlists
		saveWishlist() {
			var json = JSON.stringify(angular.copy(this.wishlists));
		},// save Cart


		// load Wishlists lên
		loadWishlistByUsername() {
			var json = localStorage.getItem("user");
			var username = JSON.parse(json).username;
			if (username) {
				$http.get(`/TechnoShop/rest/wishList/${username}`).then(resp => {
					this.wishlists = resp.data ? resp.data : [];
				})
			}
		},// save Cart

	}
	$scope.wishlist.loadWishlistByUsername();
}); 