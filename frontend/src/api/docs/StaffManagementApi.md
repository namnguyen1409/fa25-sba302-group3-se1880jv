# StaffManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**create11**](#create11) | **POST** /api/admin/staffs | |
|[**createStaffSchedule**](#createstaffschedule) | **POST** /api/admin/staffs/{staffId}/schedule | |
|[**createStaffScheduleTemplate**](#createstaffscheduletemplate) | **POST** /api/admin/staffs/{staffId}/schedule-template | |
|[**dayOff**](#dayoff) | **POST** /api/admin/staffs/schedule/day-off | |
|[**delete11**](#delete11) | **DELETE** /api/admin/staffs/{id} | |
|[**deleteStaffSchedule**](#deletestaffschedule) | **DELETE** /api/admin/staffs/schedule/{scheduleId} | |
|[**deleteStaffScheduleTemplate**](#deletestaffscheduletemplate) | **DELETE** /api/admin/staffs/schedule-template/{templateId} | |
|[**filter13**](#filter13) | **POST** /api/admin/staffs/filter | |
|[**generate**](#generate) | **POST** /api/admin/staffs/{staffId}/schedule/generate | |
|[**getById7**](#getbyid7) | **GET** /api/admin/staffs/{id} | |
|[**getMySchedule**](#getmyschedule) | **POST** /api/admin/staffs/schedule/filter | |
|[**getStaffSchedule**](#getstaffschedule) | **POST** /api/admin/staffs/{staffId}/schedule/filter | |
|[**getStaffScheduleTemplate**](#getstaffscheduletemplate) | **POST** /api/admin/staffs/{staffId}/schedule-template/filter | |
|[**markStatus**](#markstatus) | **PATCH** /api/admin/staffs/schedule/{scheduleId}/status | |
|[**myRange**](#myrange) | **GET** /api/admin/staffs/schedule | |
|[**range**](#range) | **GET** /api/admin/staffs/{staffId}/schedule | |
|[**update11**](#update11) | **PUT** /api/admin/staffs/{id} | |
|[**updateStaffSchedule**](#updatestaffschedule) | **PUT** /api/admin/staffs/schedule/{scheduleId} | |
|[**updateStaffScheduleTemplate**](#updatestaffscheduletemplate) | **PUT** /api/admin/staffs/schedule-template/{templateId} | |

# **create11**
> CustomApiResponseStaffResponse create11(staffRequest)


### Example

```typescript
import {
    StaffManagementApi,
    Configuration,
    StaffRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let staffRequest: StaffRequest; //

const { status, data } = await apiInstance.create11(
    staffRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **staffRequest** | **StaffRequest**|  | |


### Return type

**CustomApiResponseStaffResponse**

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

# **createStaffSchedule**
> CustomApiResponseStaffScheduleResponse createStaffSchedule(staffScheduleRequest)


### Example

```typescript
import {
    StaffManagementApi,
    Configuration,
    StaffScheduleRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let staffId: string; // (default to undefined)
let staffScheduleRequest: StaffScheduleRequest; //

const { status, data } = await apiInstance.createStaffSchedule(
    staffId,
    staffScheduleRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **staffScheduleRequest** | **StaffScheduleRequest**|  | |
| **staffId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseStaffScheduleResponse**

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

# **createStaffScheduleTemplate**
> CustomApiResponseStaffScheduleTemplateResponse createStaffScheduleTemplate(staffScheduleTemplateRequest)


### Example

```typescript
import {
    StaffManagementApi,
    Configuration,
    StaffScheduleTemplateRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let staffId: string; // (default to undefined)
let staffScheduleTemplateRequest: StaffScheduleTemplateRequest; //

const { status, data } = await apiInstance.createStaffScheduleTemplate(
    staffId,
    staffScheduleTemplateRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **staffScheduleTemplateRequest** | **StaffScheduleTemplateRequest**|  | |
| **staffId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseStaffScheduleTemplateResponse**

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

# **dayOff**
> CustomApiResponseStaffScheduleResponse dayOff(staffScheduleDayOffRequest)


### Example

```typescript
import {
    StaffManagementApi,
    Configuration,
    StaffScheduleDayOffRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let staffScheduleDayOffRequest: StaffScheduleDayOffRequest; //

const { status, data } = await apiInstance.dayOff(
    staffScheduleDayOffRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **staffScheduleDayOffRequest** | **StaffScheduleDayOffRequest**|  | |


### Return type

**CustomApiResponseStaffScheduleResponse**

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

# **delete11**
> CustomApiResponseVoid delete11()


### Example

```typescript
import {
    StaffManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.delete11(
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

# **deleteStaffSchedule**
> CustomApiResponseVoid deleteStaffSchedule()


### Example

```typescript
import {
    StaffManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let scheduleId: string; // (default to undefined)

const { status, data } = await apiInstance.deleteStaffSchedule(
    scheduleId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **scheduleId** | [**string**] |  | defaults to undefined|


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

# **deleteStaffScheduleTemplate**
> CustomApiResponseVoid deleteStaffScheduleTemplate()


### Example

```typescript
import {
    StaffManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let templateId: string; // (default to undefined)

const { status, data } = await apiInstance.deleteStaffScheduleTemplate(
    templateId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **templateId** | [**string**] |  | defaults to undefined|


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

# **filter13**
> CustomApiResponsePageStaffResponse filter13(searchFilter)


### Example

```typescript
import {
    StaffManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter13(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageStaffResponse**

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

# **generate**
> CustomApiResponseVoid generate()


### Example

```typescript
import {
    StaffManagementApi,
    Configuration,
    StaffScheduleGenerateRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let staffId: string; // (default to undefined)
let staffScheduleGenerateRequest: StaffScheduleGenerateRequest; // (optional)

const { status, data } = await apiInstance.generate(
    staffId,
    staffScheduleGenerateRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **staffScheduleGenerateRequest** | **StaffScheduleGenerateRequest**|  | |
| **staffId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseVoid**

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

# **getById7**
> CustomApiResponseStaffResponse getById7()


### Example

```typescript
import {
    StaffManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.getById7(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseStaffResponse**

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

# **getMySchedule**
> CustomApiResponsePageStaffScheduleResponse getMySchedule(searchFilter)


### Example

```typescript
import {
    StaffManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.getMySchedule(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageStaffScheduleResponse**

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

# **getStaffSchedule**
> CustomApiResponsePageStaffScheduleResponse getStaffSchedule(searchFilter)


### Example

```typescript
import {
    StaffManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let staffId: string; // (default to undefined)
let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.getStaffSchedule(
    staffId,
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |
| **staffId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePageStaffScheduleResponse**

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

# **getStaffScheduleTemplate**
> CustomApiResponsePageStaffScheduleTemplateResponse getStaffScheduleTemplate(searchFilter)


### Example

```typescript
import {
    StaffManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let staffId: string; // (default to undefined)
let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.getStaffScheduleTemplate(
    staffId,
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |
| **staffId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePageStaffScheduleTemplateResponse**

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

# **markStatus**
> CustomApiResponseStaffScheduleResponse markStatus()


### Example

```typescript
import {
    StaffManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let scheduleId: string; // (default to undefined)
let status: 'AVAILABLE' | 'OFF' | 'CANCELLED' | 'CHANGED'; // (default to undefined)
let note: string; // (optional) (default to undefined)

const { status, data } = await apiInstance.markStatus(
    scheduleId,
    status,
    note
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **scheduleId** | [**string**] |  | defaults to undefined|
| **status** | [**&#39;AVAILABLE&#39; | &#39;OFF&#39; | &#39;CANCELLED&#39; | &#39;CHANGED&#39;**]**Array<&#39;AVAILABLE&#39; &#124; &#39;OFF&#39; &#124; &#39;CANCELLED&#39; &#124; &#39;CHANGED&#39;>** |  | defaults to undefined|
| **note** | [**string**] |  | (optional) defaults to undefined|


### Return type

**CustomApiResponseStaffScheduleResponse**

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

# **myRange**
> CustomApiResponseListStaffScheduleResponse myRange()


### Example

```typescript
import {
    StaffManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let from: string; // (default to undefined)
let to: string; // (default to undefined)

const { status, data } = await apiInstance.myRange(
    from,
    to
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **from** | [**string**] |  | defaults to undefined|
| **to** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseListStaffScheduleResponse**

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

# **range**
> CustomApiResponseListStaffScheduleResponse range()


### Example

```typescript
import {
    StaffManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let staffId: string; // (default to undefined)
let from: string; // (default to undefined)
let to: string; // (default to undefined)

const { status, data } = await apiInstance.range(
    staffId,
    from,
    to
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **staffId** | [**string**] |  | defaults to undefined|
| **from** | [**string**] |  | defaults to undefined|
| **to** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseListStaffScheduleResponse**

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

# **update11**
> CustomApiResponseStaffResponse update11(staffRequest)


### Example

```typescript
import {
    StaffManagementApi,
    Configuration,
    StaffRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let id: string; // (default to undefined)
let staffRequest: StaffRequest; //

const { status, data } = await apiInstance.update11(
    id,
    staffRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **staffRequest** | **StaffRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseStaffResponse**

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

# **updateStaffSchedule**
> CustomApiResponseStaffScheduleResponse updateStaffSchedule(staffScheduleRequest)


### Example

```typescript
import {
    StaffManagementApi,
    Configuration,
    StaffScheduleRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let scheduleId: string; // (default to undefined)
let staffScheduleRequest: StaffScheduleRequest; //

const { status, data } = await apiInstance.updateStaffSchedule(
    scheduleId,
    staffScheduleRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **staffScheduleRequest** | **StaffScheduleRequest**|  | |
| **scheduleId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseStaffScheduleResponse**

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

# **updateStaffScheduleTemplate**
> CustomApiResponseStaffScheduleTemplateResponse updateStaffScheduleTemplate(staffScheduleTemplateRequest)


### Example

```typescript
import {
    StaffManagementApi,
    Configuration,
    StaffScheduleTemplateRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new StaffManagementApi(configuration);

let templateId: string; // (default to undefined)
let staffScheduleTemplateRequest: StaffScheduleTemplateRequest; //

const { status, data } = await apiInstance.updateStaffScheduleTemplate(
    templateId,
    staffScheduleTemplateRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **staffScheduleTemplateRequest** | **StaffScheduleTemplateRequest**|  | |
| **templateId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseStaffScheduleTemplateResponse**

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

