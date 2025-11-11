# ClinicManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**create2**](#create2) | **POST** /api/organization/clinics | |
|[**delete2**](#delete2) | **DELETE** /api/organization/clinics/{id} | |
|[**filter3**](#filter3) | **POST** /api/organization/clinics/filter | |
|[**filterDepartmentsByClinic**](#filterdepartmentsbyclinic) | **POST** /api/organization/clinics/{clinicId}/departments/filter | |
|[**getClinicById**](#getclinicbyid) | **GET** /api/organization/clinics/{id} | |
|[**getDefaultClinic**](#getdefaultclinic) | **GET** /api/organization/clinics | |
|[**update2**](#update2) | **PUT** /api/organization/clinics/{id} | |

# **create2**
> CustomApiResponseClinicResponse create2(clinicRequest)


### Example

```typescript
import {
    ClinicManagementApi,
    Configuration,
    ClinicRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ClinicManagementApi(configuration);

let clinicRequest: ClinicRequest; //

const { status, data } = await apiInstance.create2(
    clinicRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **clinicRequest** | **ClinicRequest**|  | |


### Return type

**CustomApiResponseClinicResponse**

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

# **delete2**
> CustomApiResponseVoid delete2()


### Example

```typescript
import {
    ClinicManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ClinicManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.delete2(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


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

# **filter3**
> CustomApiResponsePageClinicResponse filter3(searchFilter)


### Example

```typescript
import {
    ClinicManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new ClinicManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter3(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageClinicResponse**

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

# **filterDepartmentsByClinic**
> CustomApiResponsePageDepartmentResponse filterDepartmentsByClinic(searchFilter)


### Example

```typescript
import {
    ClinicManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new ClinicManagementApi(configuration);

let clinicId: string; // (default to undefined)
let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filterDepartmentsByClinic(
    clinicId,
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |
| **clinicId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePageDepartmentResponse**

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

# **getClinicById**
> CustomApiResponseClinicResponse getClinicById()


### Example

```typescript
import {
    ClinicManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ClinicManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.getClinicById(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseClinicResponse**

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

# **getDefaultClinic**
> CustomApiResponseClinicResponse getDefaultClinic()


### Example

```typescript
import {
    ClinicManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ClinicManagementApi(configuration);

const { status, data } = await apiInstance.getDefaultClinic();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseClinicResponse**

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

# **update2**
> CustomApiResponseClinicResponse update2(clinicRequest)


### Example

```typescript
import {
    ClinicManagementApi,
    Configuration,
    ClinicRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ClinicManagementApi(configuration);

let id: string; // (default to undefined)
let clinicRequest: ClinicRequest; //

const { status, data } = await apiInstance.update2(
    id,
    clinicRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **clinicRequest** | **ClinicRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseClinicResponse**

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

