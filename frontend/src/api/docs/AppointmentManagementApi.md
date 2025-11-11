# AppointmentManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**create10**](#create10) | **POST** /api/appointments | |
|[**delete10**](#delete10) | **DELETE** /api/appointments/{appointmentId} | |
|[**filter11**](#filter11) | **POST** /api/appointments/filter | |
|[**getById6**](#getbyid6) | **GET** /api/appointments/{appointmentId} | |
|[**update10**](#update10) | **PUT** /api/appointments/{appointmentId} | |

# **create10**
> CustomApiResponseAppointmentResponse create10(appointmentRequest)


### Example

```typescript
import {
    AppointmentManagementApi,
    Configuration,
    AppointmentRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new AppointmentManagementApi(configuration);

let appointmentRequest: AppointmentRequest; //

const { status, data } = await apiInstance.create10(
    appointmentRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **appointmentRequest** | **AppointmentRequest**|  | |


### Return type

**CustomApiResponseAppointmentResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **delete10**
> CustomApiResponseVoid delete10()


### Example

```typescript
import {
    AppointmentManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AppointmentManagementApi(configuration);

let appointmentId: string; // (default to undefined)

const { status, data } = await apiInstance.delete10(
    appointmentId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **appointmentId** | [**string**] |  | defaults to undefined|


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

# **filter11**
> CustomApiResponsePageAppointmentResponse filter11(searchFilter)


### Example

```typescript
import {
    AppointmentManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new AppointmentManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter11(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageAppointmentResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getById6**
> CustomApiResponseAppointmentResponse getById6()


### Example

```typescript
import {
    AppointmentManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AppointmentManagementApi(configuration);

let appointmentId: string; // (default to undefined)

const { status, data } = await apiInstance.getById6(
    appointmentId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **appointmentId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseAppointmentResponse**

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

# **update10**
> CustomApiResponseAppointmentResponse update10(appointmentRequest)


### Example

```typescript
import {
    AppointmentManagementApi,
    Configuration,
    AppointmentRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new AppointmentManagementApi(configuration);

let appointmentId: string; // (default to undefined)
let appointmentRequest: AppointmentRequest; //

const { status, data } = await apiInstance.update10(
    appointmentId,
    appointmentRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **appointmentRequest** | **AppointmentRequest**|  | |
| **appointmentId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseAppointmentResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

