# AccountDeviceControllerApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAllDevices**](AccountDeviceControllerApi.md#getalldevices) | **GET** /api/account/devices |  |
| [**logoutAllDevices**](AccountDeviceControllerApi.md#logoutalldevices) | **POST** /api/account/devices/logout-all |  |
| [**logoutDevice**](AccountDeviceControllerApi.md#logoutdevice) | **POST** /api/account/devices/logout/{deviceSessionId} |  |



## getAllDevices

> CustomApiResponseListDeviceSessionResponse getAllDevices()



### Example

```ts
import {
  Configuration,
  AccountDeviceControllerApi,
} from '';
import type { GetAllDevicesRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountDeviceControllerApi(config);

  try {
    const data = await api.getAllDevices();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**CustomApiResponseListDeviceSessionResponse**](CustomApiResponseListDeviceSessionResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## logoutAllDevices

> CustomApiResponseVoid logoutAllDevices()



### Example

```ts
import {
  Configuration,
  AccountDeviceControllerApi,
} from '';
import type { LogoutAllDevicesRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountDeviceControllerApi(config);

  try {
    const data = await api.logoutAllDevices();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## logoutDevice

> CustomApiResponseVoid logoutDevice(deviceSessionId)



### Example

```ts
import {
  Configuration,
  AccountDeviceControllerApi,
} from '';
import type { LogoutDeviceRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountDeviceControllerApi(config);

  const body = {
    // string
    deviceSessionId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies LogoutDeviceRequest;

  try {
    const data = await api.logoutDevice(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **deviceSessionId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)

