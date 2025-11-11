# AccountDeviceControllerApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**getAllDevices**](#getalldevices) | **GET** /api/account/devices | |
|[**logoutAllDevices**](#logoutalldevices) | **POST** /api/account/devices/logout-all | |
|[**logoutDevice**](#logoutdevice) | **POST** /api/account/devices/logout/{deviceSessionId} | |

# **getAllDevices**
> CustomApiResponseListDeviceSessionResponse getAllDevices()


### Example

```typescript
import {
    AccountDeviceControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountDeviceControllerApi(configuration);

const { status, data } = await apiInstance.getAllDevices();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseListDeviceSessionResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **logoutAllDevices**
> CustomApiResponseVoid logoutAllDevices()


### Example

```typescript
import {
    AccountDeviceControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountDeviceControllerApi(configuration);

const { status, data } = await apiInstance.logoutAllDevices();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseVoid**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **logoutDevice**
> CustomApiResponseVoid logoutDevice()


### Example

```typescript
import {
    AccountDeviceControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountDeviceControllerApi(configuration);

let deviceSessionId: string; // (default to undefined)

const { status, data } = await apiInstance.logoutDevice(
    deviceSessionId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **deviceSessionId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseVoid**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

